package com.practice.spring.repository;

import com.practice.spring.entity.BiryaniBO;
import com.practice.spring.entity.CurriesBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriesRepository extends JpaRepository<CurriesBO, Integer> {

}
