package com.generation.application.persistence.repository.impl;

import com.generation.application.persistence.entity.StudentEntity;
import com.generation.application.persistence.repository.StudentRepository;
import com.generation.domain.gateway.GetStudentByIdGateway;
import com.generation.domain.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FindStudentByIdRepository implements GetStudentByIdGateway {

    private final StudentRepository repository;

    /**
     * Finds a student by their ID and converts the result to a Student model object.
     *
     * @param id the ID of the student to be found
     * @return an Optional containing the Student model object if found, or an empty Optional if not found
     */
    @Override
    public Optional<Student> execute(Long id) {
        return repository.findById(id).map(StudentEntity::toModel);
    }

}
