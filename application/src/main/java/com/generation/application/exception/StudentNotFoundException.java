package com.generation.application.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException() {
        super("student not found");
    }
}
