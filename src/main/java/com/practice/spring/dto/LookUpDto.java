package com.practice.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LookUpDto {
    private Integer id;

    private Integer type;

    private String name;

    private Integer price;
}
