package com.generation.application.persistence.repository.impl;

import com.generation.application.persistence.entity.StudentEntity;
import com.generation.application.persistence.repository.StudentRepository;
import com.generation.domain.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class InsertStudentRepositoryTest {

    @Autowired
    private InsertStudentRepository insertStudentRepository;

    @Autowired
    private StudentRepository baseRepository;

    @Test
    void given_student_when_repository_is_executed_then_insert_student_successfully() {
        // given
        Student student = new Student(
                "John Doe",
                25,
                8.0,
                9.0,
                8.5
        );

        // when
        insertStudentRepository.execute(student);

        // then
        Optional<StudentEntity> result = baseRepository.findById(1L);
        assertTrue(result.isPresent());
    }

    @Test
    void given_null_student_when_repository_is_executed_then_throw_exception() {
        // when
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> insertStudentRepository.execute(null)
        );

        // then
        assertEquals("Cannot invoke \"com.generation.domain.model.Student.getFullName()\" because \"student\" is null", exception.getMessage());
    }

}