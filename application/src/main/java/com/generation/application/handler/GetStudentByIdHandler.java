package com.generation.application.handler;

import com.generation.application.dto.StudentDTO;
import com.generation.application.exception.StudentNotFoundException;
import com.generation.domain.model.Student;
import com.generation.domain.usecase.GetStudentByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetStudentByIdHandler {

    private final GetStudentByIdUseCase getStudentByIdUseCase;

    /**
     * Handles the retrieval of a student by their ID.
     *
     * @param id the ID of the student to be retrieved
     * @return a StudentDTO object representing the student
     * @throws StudentNotFoundException if the student with the given ID is not found
     */
    public StudentDTO handle(Long id) {
        Optional<Student> student = getStudentByIdUseCase.execute(id);

        if (student.isEmpty()) {
            throw new StudentNotFoundException();
        }

        return StudentDTO.fromModel(student.get());
    }

}
