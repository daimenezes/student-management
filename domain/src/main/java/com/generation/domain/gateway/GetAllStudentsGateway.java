package com.generation.domain.gateway;

import com.generation.domain.model.Student;

import java.util.List;

public interface GetAllStudentsGateway {

    List<Student> execute();
}
