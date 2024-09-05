package com.generation.domain.gateway;

import com.generation.domain.model.Student;

import java.util.Optional;

public interface GetStudentByIdGateway {

    Optional<Student> execute(Long id);
}
