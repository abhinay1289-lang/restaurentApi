package com.practice.spring.repository;

import com.practice.spring.entity.BiryaniBO;
import com.practice.spring.entity.RiceBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiceRepository extends JpaRepository<RiceBO, Integer> {

}
