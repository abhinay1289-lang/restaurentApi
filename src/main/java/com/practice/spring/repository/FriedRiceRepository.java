package com.practice.spring.repository;

import com.practice.spring.entity.BiryaniBO;
import com.practice.spring.entity.FriedRiceBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriedRiceRepository extends JpaRepository<FriedRiceBO, Integer> {

}
