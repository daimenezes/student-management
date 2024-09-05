package com.generation.application.handler;

import com.generation.application.dto.StudentDTO;
import com.generation.domain.model.Student;
import com.generation.domain.usecase.SaveStudentUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveStudentHandler {

    private final SaveStudentUseCase saveStudentUseCase;

    /**
     * Handles the saving of a student.
     *
     * @param input the StudentDTO object containing the student data to be saved
     */
    public void handle(StudentDTO input) {
        Student student = input.toModel();
        saveStudentUseCase.execute(student);
    }

}
