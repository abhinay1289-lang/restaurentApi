package com.practice.spring.service;

import com.practice.spring.constants.LookupType;
import com.practice.spring.dto.LookUpDto;

import java.util.List;

public interface LookupService {
  public <T, I> List<LookUpDto> getLookupValues(LookupType type);

  public LookUpDto getSkills();

  public void clearCache(LookupType type);

  public <T, I> LookUpDto saveLookupDataById(I id,LookupType lookupType);

  public <T, I> List<LookUpDto> saveLookupData(List<LookUpDto> lookupData, LookupType type);

  public <T, I> void deleteLookup(String type, Integer id);
}
