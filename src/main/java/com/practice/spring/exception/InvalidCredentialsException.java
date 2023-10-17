package com.practice.spring.exception;

import lombok.Data;

@Data
public class InvalidCredentialsException extends RuntimeException{
    final String message;

    public InvalidCredentialsException(String message){ this.message = message; }
}
