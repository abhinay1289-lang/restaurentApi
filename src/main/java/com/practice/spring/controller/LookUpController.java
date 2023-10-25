package com.practice.spring.controller;

import com.practice.spring.constants.LookupType;
import com.practice.spring.dto.LookUpDto;
import com.practice.spring.service.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
