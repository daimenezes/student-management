package com.generation.domain.usecase;

import com.generation.domain.gateway.DeleteStudentGateway;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class DeleteStudentUseCase {

    @Inject
    private final DeleteStudentGateway gateway;

    public DeleteStudentUseCase(DeleteStudentGateway gateway) {
        this.gateway = gateway;
    }

    /**
     * Executes the deletion of a student by their ID.
     *
     * @param id the ID of the student to be deleted
     * @throws IllegalArgumentException if the ID is null or less than or equal to 0
     */
    public void execute(Long id) {
        validate(id);
        gateway.execute(id);
    }

    private void validate(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("id cannot be null or less than or equal to 0");
        }
    }

}
