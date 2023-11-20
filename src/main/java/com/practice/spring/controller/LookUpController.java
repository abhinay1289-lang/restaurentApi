package com.practice.spring.controller;

import com.practice.spring.constants.LookupType;
import com.practice.spring.dto.AppResponseDto;
import com.practice.spring.dto.LookUpDto;
import com.practice.spring.service.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/lookup")
public class LookUpController {

    @Autowired
    LookupService lookupService;

    @PostMapping("/{type}")
    public List<LookUpDto> saveLookupData(
            @RequestBody List<LookUpDto> lookupData, @PathVariable("type") String type) {
        List<LookUpDto> response = lookupService.saveLookupData(lookupData, LookupType.get(type));
        lookupService.clearCache(LookupType.get(type));
        return response;
    }

    @PatchMapping("/{type}/{id}")
    public AppResponseDto updateLookupDataById(@PathVariable("type") String type, @PathVariable("id") Integer id){
        lookupService.saveLookupDataById(id,LookupType.get(type));
        lookupService.clearCache(LookupType.get(type));
        return new AppResponseDto(null, "item updated successfully");
    }

    @GetMapping("/{type}")
    public Map<String, List<LookUpDto>> getLookupData(@PathVariable("type") String type ){
        Map<String, List<LookUpDto>> result = new HashMap<>();
        if ("all".equalsIgnoreCase(type)) {
            Stream.of(LookupType.values())
                    .forEach(lookup -> result.put(lookup.getType(), lookupService.getLookupValues(lookup)));
        } else {
            result.put(type, lookupService.getLookupValues(LookupType.get(type)));
        }
        return result;
    }
    @DeleteMapping("/{type}/{id}")
    public AppResponseDto deleteLookupDataById(@PathVariable("type") String type,@PathVariable("id") Integer id){
        lookupService.deleteLookup(type,id);
        lookupService.clearCache(LookupType.get(type));
        return new AppResponseDto(null, "Data deleted successfully");
    }

    @GetMapping("/prices")
    public <T, I> List<LookUpDto> getLookupData(@RequestParam("integers") List<Integer> integers ,@RequestParam("type") String type)
    {
        return lookupService.getLookupData(integers,LookupType.get(type));
    }
}
