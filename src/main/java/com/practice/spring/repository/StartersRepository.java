package com.practice.spring.repository;

import com.practice.spring.entity.BiryaniBO;
import com.practice.spring.entity.StartersBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StartersRepository extends JpaRepository<StartersBO, Integer> {

}
