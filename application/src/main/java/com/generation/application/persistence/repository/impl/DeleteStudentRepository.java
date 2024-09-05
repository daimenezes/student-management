package com.generation.application.persistence.repository.impl;

import com.generation.application.persistence.repository.StudentRepository;
import com.generation.domain.gateway.DeleteStudentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeleteStudentRepository implements DeleteStudentGateway {

    private final StudentRepository repository;

    /**
     * Deletes a student by their ID.
     *
     * @param id the ID of the student to be deleted
     */
    @Override
    public void execute(Long id) {
        repository.deleteById(id);
    }

}
