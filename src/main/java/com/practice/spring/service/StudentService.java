package com.practice.spring.service;

import com.practice.spring.dto.StudentDto;
import com.practice.spring.dto.StudentPartialUpdateDto;
import com.practice.spring.dto.UserCredentialsDto;
import com.practice.spring.entity.UserdetailsBO;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();

    UserdetailsBO validateUserCredentials(UserCredentialsDto loginRequest);

    StudentDto getStudentById(Integer id);

    UserdetailsBO saveStudent(StudentDto studentDto);

    String updateStudentById(StudentDto studentDto, Integer id);

    String deleteStudentById(Integer id);

    String updateStudentPartiallyById(StudentPartialUpdateDto studentPartialUpdateDto, Integer id);

    List<UserdetailsBO> saveStudents(List<StudentDto> studentDtos);

}
