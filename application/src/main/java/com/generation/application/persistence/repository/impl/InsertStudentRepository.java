package com.generation.application.persistence.repository.impl;

import com.generation.application.persistence.entity.StudentEntity;
import com.generation.application.persistence.repository.StudentRepository;
import com.generation.domain.gateway.SaveStudentGateway;
import com.generation.domain.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InsertStudentRepository implements SaveStudentGateway {

    private final StudentRepository repository;

    /**
     * Executes the creation of a new student.
     *
     * @param student the Student model object containing the student data to be saved
     */
    @Override
    public void execute(Student student) {
        StudentEntity entity = StudentEntity.fromModel(student);
        repository.save(entity);
    }

}
