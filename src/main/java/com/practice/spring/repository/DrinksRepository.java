package com.practice.spring.repository;

import com.practice.spring.entity.BiryaniBO;
import com.practice.spring.entity.DrinksBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinksRepository extends JpaRepository<DrinksBO, Integer> {

}
