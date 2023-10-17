package com.practice.spring.dto;

import lombok.Data;

@Data
public class StudentPartialUpdateDto {
    private int id;
    private String emailId;
    private Long phoneNo;
}
