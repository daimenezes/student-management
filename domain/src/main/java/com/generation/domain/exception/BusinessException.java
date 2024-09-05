package com.generation.domain.exception;

public class BusinessException extends IllegalArgumentException {

    public BusinessException(String message) {
        super(message);
    }

}
