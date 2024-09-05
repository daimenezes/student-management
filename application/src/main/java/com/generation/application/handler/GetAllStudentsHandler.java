package com.generation.application.handler;

import com.generation.application.dto.StudentDTO;
import com.generation.domain.model.Student;
import com.generation.domain.usecase.GetAllStudentsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllStudentsHandler {

    private final GetAllStudentsUseCase getAllStudentsUseCase;

    /**
     * Handles the retrieval of all students and converts them to StudentDTOs.
     *
     * @return a list of StudentDTO objects representing all students
     */
    public List<StudentDTO> handle() {
        List<Student> students = getAllStudentsUseCase.execute();
        return StudentDTO.fromModel(students);
    }

}
