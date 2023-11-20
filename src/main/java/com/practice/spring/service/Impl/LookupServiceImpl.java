package com.practice.spring.service.Impl;

import com.practice.spring.constants.LookupType;
import com.practice.spring.dto.LookUpDto;
import com.practice.spring.entity.*;
import com.practice.spring.repository.*;
import com.practice.spring.service.LookupService;
import com.practice.spring.util.LookupInfo;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.metamodel.EntityType;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Optional;
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
        LookupInfo<JpaRepository, Class> lookupInfo = lookupMap.get(type);
        JpaRepository<T, I> repo = (JpaRepository<T, I>) lookupInfo.getRepo();
            List<T> listofbiryani = repo.findAll();
        return  StreamSupport.stream(listofbiryani.spliterator(), false)
                .map(s -> mapper.map(s, LookUpDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public LookUpDto getSkills() {
        return null;
    }

    @Override
    @CacheEvict(key = "#type", cacheNames = "lookup")
    public void clearCache(LookupType type) {

    }

    @Override
    public <T, I>LookUpDto saveLookupDataById(I id, LookupType type) {
        LookupInfo<JpaRepository, Class> lookupInfo = lookupMap.get(type);
        JpaRepository<T, I> repo = (JpaRepository<T, I>) lookupInfo.getRepo();
        Optional<T>  item = repo.findById(id);


        return null;
    }

    @Override
    public <T, I> List<LookUpDto> saveLookupData(List<LookUpDto> lookupData, LookupType type) {
        LookupInfo<JpaRepository, Class> lookupInfo = lookupMap.get(type);
        JpaRepository<T, I> repo = (JpaRepository<T, I>) lookupInfo.getRepo();
        Class<T> destClass = (Class<T>) lookupInfo.getLookupClass();
        Iterable<T> result =
                repo.saveAll(
                        lookupData.stream().map(s -> mapper.map(s, destClass)).collect(Collectors.toList()));
        return StreamSupport.stream(result.spliterator(), false)
                .map(s -> mapper.map(s, LookUpDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public <T, I> void deleteLookup(String type, Integer id) {
        if (LookupType.isLookup(type)) {
            LookupInfo<CrudRepository, Class> lookupInfo = lookupMap.get(LookupType.get(type));
            JpaRepository<T, I> repo = (JpaRepository<T, I>)  lookupInfo.getRepo();
            repo.deleteById((I) id);
        }
    }

    @Override
    public List<LookUpDto> getLookupData(List<Integer> integers, LookupType type) {
        LookupInfo<JpaRepository, Class> lookupInfo = lookupMap.get(type);
        JpaRepository repo = (JpaRepository) lookupInfo.getRepo();

        List<LookUpDto> result = integers.stream()
                .map(data -> repo.findById(data))
                .filter(Optional::isPresent)
                .map(optional -> mapEntityToDto((BiryaniBO) optional.get()))
                .collect(Collectors.toList());
        return result;
    }
    private LookUpDto mapEntityToDto(BiryaniBO entity) {
        LookUpDto dto = new LookUpDto();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        return dto;
    }
}
