package com.example.fake_api_us.infrastructure.exception;

public class BusinessException extends RuntimeException{

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable couse) {
        super(message, couse);
    }
}
