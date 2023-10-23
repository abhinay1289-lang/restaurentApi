package com.practice.spring.service;

import com.practice.spring.constants.LookupType;
import com.practice.spring.dto.LookUpDto;

import java.util.List;

public interface LookupService {
  public <T, I> List<LookUpDto> getLookupValues(LookupType type);

  public List<LookUpDto> getSkills();

  public void clearCache(LookupType type);

  public <T, I> List<LookUpDto> saveLookupData(List<LookUpDto> lookupData, LookupType type);

//  public List<SkillBO> saveAllSkills(List<LookUpDto> lookupData);

  public <T, I> void deleteLookup(String type, Integer id);
}
