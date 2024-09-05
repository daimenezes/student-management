package com.generation.domain.usecase;

import com.generation.domain.exception.BusinessException;
import com.generation.domain.gateway.GetStudentByIdGateway;
import com.generation.domain.model.Student;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Optional;

@Named
public class GetStudentByIdUseCase {

    @Inject
    private final GetStudentByIdGateway gateway;

    public GetStudentByIdUseCase(GetStudentByIdGateway gateway) {
        this.gateway = gateway;
    }

    /**
     * Retrieves a student by their ID.
     *
     * @param id the ID of the student to be retrieved
     * @return an Optional containing the Student if found, or an empty Optional if not found
     * @throws BusinessException if the ID is null or less than or equal to 0
     */
    public Optional<Student> execute(Long id) {
        validate(id);
        return gateway.execute(id);
    }

    private void validate(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessException("id is required and must be greater than 0");
        }
    }

}
