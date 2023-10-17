package com.practice.spring.repository;

import com.practice.spring.entity.UserdetailsBO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserdetailsBO,Integer> {
    Optional<UserdetailsBO> findByEmailId(String username);
}
