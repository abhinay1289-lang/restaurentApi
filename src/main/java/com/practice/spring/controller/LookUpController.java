package com.practice.spring.controller;

import com.practice.spring.constants.LookupType;
import com.practice.spring.dto.LookUpDto;
import com.practice.spring.service.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class LookUpController {

    @Autowired
    private LookupService lookupService;
    @PostMapping("/{type}")
    public List<LookUpDto> saveLookupData(
            @RequestBody List<LookUpDto> lookupData, @PathVariable("type") String type) {
        System.out.println("lookupdata..."+lookupData+"     "+type);
        List<LookUpDto> response = lookupService.saveLookupData(lookupData, LookupType.get(type));
        lookupService.clearCache(LookupType.get(type));
        return response;
    }
}
