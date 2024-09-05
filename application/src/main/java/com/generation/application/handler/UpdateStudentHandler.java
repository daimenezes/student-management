package com.generation.application.handler;

import com.generation.application.dto.StudentDTO;
import com.generation.domain.model.Student;
import com.generation.domain.usecase.UpdateStudentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateStudentHandler {

    private final UpdateStudentUseCase updateStudentUseCase;

    /**
     * Handles the update of a student by their ID.
     *
     * @param id    the ID of the student to be updated
     * @param input the StudentDTO object containing the updated student data
     * @return a StudentDTO object representing the updated student
     */
    public StudentDTO handle(Long id, StudentDTO input) {
        Student student = input.toModel();
        Student output = updateStudentUseCase.execute(id, student);
        return StudentDTO.fromModel(output);
    }

}
