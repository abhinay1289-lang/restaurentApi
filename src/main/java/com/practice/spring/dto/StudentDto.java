package com.practice.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String emailId;
    private Long phoneNo;
    private String collegeName;
}
