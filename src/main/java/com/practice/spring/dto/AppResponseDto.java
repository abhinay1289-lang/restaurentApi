package com.practice.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppResponseDto<T> {
    private T result;
    private String message;
}
