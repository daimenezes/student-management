package com.generation.application.persistence.repository.impl;

import com.generation.application.persistence.entity.StudentEntity;
import com.generation.application.persistence.repository.StudentRepository;
import com.generation.domain.gateway.GetAllStudentsGateway;
import com.generation.domain.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FindAllStudentsRepository implements GetAllStudentsGateway {

    private final StudentRepository repository;

    /**
     * Retrieves all students from the repository and converts them to a list of Student model objects.
     *
     * @return a list of Student model objects representing all students in the repository
     */
    @Override
    public List<Student> execute() {
        return repository.findAll()
                .stream()
                .map(StudentEntity::toModel)
                .toList();
    }

}
