package com.generation.application.handler;

import com.generation.domain.usecase.DeleteStudentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeleteStudentHandler {

    private final DeleteStudentUseCase deleteStudentUseCase;

    /**
     * Handles the deletion of a student by their ID.
     *
     * @param id the ID of the student to be deleted
     */
    public void handle(Long id) {
        deleteStudentUseCase.execute(id);
    }

}