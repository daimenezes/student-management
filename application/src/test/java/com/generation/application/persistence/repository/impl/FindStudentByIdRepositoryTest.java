package com.generation.application.persistence.repository.impl;

import com.generation.application.persistence.entity.StudentEntity;
import com.generation.application.persistence.repository.StudentRepository;
import com.generation.domain.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class FindStudentByIdRepositoryTest {

    @Autowired
    private FindStudentByIdRepository findStudentByIdRepository;

    @Autowired
    private StudentRepository baseRepository;

    @Test
    void given_valid_id_when_repository_is_executed_then_return_student_successfully() {
        // given
        StudentEntity student = new StudentEntity(
                null,
                "John Doe",
                25,
                8.0,
                9.0,
                8.5
        );

        baseRepository.save(student);

        // when
        Optional<Student> result = findStudentByIdRepository.execute(student.getId());

        // then
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getFullName());
        assertEquals(25, result.get().getAge());
        assertEquals(8.0, result.get().getFirstSemesterGrade());
        assertEquals(9.0, result.get().getSecondSemesterGrade());
        assertEquals(8.5, result.get().getFinalGrade());
    }

    @Test
    void given_invalid_id_when_repository_is_executed_then_return_empty_optional() {
        // given
        StudentEntity student = new StudentEntity(
                null,
                "John Doe",
                25,
                8.0,
                9.0,
                8.5
        );

        baseRepository.save(student);

        // when
        Optional<Student> result = findStudentByIdRepository.execute(student.getId() + 1L);

        // then
        assertTrue(result.isEmpty());
    }

}