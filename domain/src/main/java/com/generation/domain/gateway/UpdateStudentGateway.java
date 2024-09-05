package com.generation.domain.gateway;

import com.generation.domain.model.Student;

public interface UpdateStudentGateway {

    Student execute(Long id, Student student);
}
