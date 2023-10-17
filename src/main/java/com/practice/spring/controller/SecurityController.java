package com.practice.spring.controller;

import com.practice.spring.dto.StudentDto;
import com.practice.spring.dto.UserCredentialsDto;
import com.practice.spring.entity.UserdetailsBO;
import com.practice.spring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {
    @Autowired
    StudentService userService;

    @PostMapping("/login")
    public UserdetailsBO login(@RequestBody UserCredentialsDto loginDto){
        return userService.validateUserCredentials(loginDto);
    }
}