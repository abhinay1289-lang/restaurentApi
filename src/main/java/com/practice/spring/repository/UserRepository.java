package com.practice.spring.repository;
import com.practice.spring.entity.UserdetailsBO;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserdetailsBO, Integer> {

}
