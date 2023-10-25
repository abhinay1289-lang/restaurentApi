package com.practice.spring.service.Impl;

import com.practice.spring.constants.LookupType;
import com.practice.spring.dto.LookUpDto;
import com.practice.spring.entity.*;
import com.practice.spring.repository.*;
import com.practice.spring.service.LookupService;
import com.practice.spring.util.LookupInfo;
import jakarta.annotation.PostConstruct;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LookupServiceImpl implements LookupService {

    @Autowired
            private BiryaniRepository biryaniRepository;

    @Autowired
    private CurriesRepository curriesRepository;
    @Autowired
    private FriedRiceRepository friedRiceRepository;

    @Autowired
    private RiceRepository riceRepository;

    @Autowired
    private RotisRepository rotisRepository;

    @Autowired
    private DrinksRepository drinksRepository;

    @Autowired
    private StartersRepository startersRepository;

    @Autowired private DozerBeanMapper mapper;

    @PostConstruct
    public void initialize() {
        lookupMap.put(
                LookupType.BIRYANI, new LookupInfo(biryaniRepository, BiryaniBO.class));
        lookupMap.put(
                LookupType.CURRIES, new LookupInfo(curriesRepository, CurriesBO.class));
        lookupMap.put(
                LookupType.FRIED_RICE_NOODLES, new LookupInfo(friedRiceRepository, FriedRiceBO.class));
        lookupMap.put(
                LookupType.RICE, new LookupInfo(riceRepository, RiceBO.class));
        lookupMap.put(
                LookupType.ROTIS, new LookupInfo(rotisRepository, RotisBO.class));
        lookupMap.put(
                LookupType.DRINKS, new LookupInfo(drinksRepository, DrinksBO.class));
        lookupMap.put(
                LookupType.STARTERS, new LookupInfo(startersRepository, StartersBO.class));
    }

    EnumMap<LookupType, LookupInfo> lookupMap = new EnumMap<>(LookupType.class);


    @Override
    public <T, I> List<LookUpDto> getLookupValues(LookupType type) {
        return null;
    }

    @Override
    public List<LookUpDto> getSkills() {
        return null;
    }

    @Override
    @CacheEvict(key = "#type", cacheNames = "lookup")
    public void clearCache(LookupType type) {

    }

    @Override
    public <T, I> List<LookUpDto> saveLookupData(List<LookUpDto> lookupData, LookupType type) {
        LookupInfo<JpaRepository, Class> lookupInfo = lookupMap.get(type);
        JpaRepository<T, I> repo = (JpaRepository<T, I>) lookupInfo.getRepo();
        Class<T> destClass = (Class<T>) lookupInfo.getLookupClass();
        Iterable<T> result =
                repo.saveAll(
                        lookupData.stream().map(s -> mapper.map(s, destClass)).collect(Collectors.toList()));
        System.out.println("destination class:"+result);
        return StreamSupport.stream(result.spliterator(), false)
                .map(s -> mapper.map(s, LookUpDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public <T, I> void deleteLookup(String type, Integer id) {

    }
}
