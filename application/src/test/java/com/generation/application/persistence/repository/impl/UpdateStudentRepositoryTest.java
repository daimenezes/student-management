package com.generation.application.persistence.repository.impl;

import com.generation.application.exception.StudentNotFoundException;
import com.generation.application.persistence.entity.StudentEntity;
import com.generation.application.persistence.repository.StudentRepository;
import com.generation.domain.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class UpdateStudentRepositoryTest {

    @Autowired
    private UpdateStudentRepository updateStudentRepository;

    @Autowired
    private StudentRepository baseRepository;

    @Test
    void given_valid_student_when_repository_is_executed_then_update_student_successfully() {
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
        Student updatedStudent = new Student(
                "Jane Doe",
                23,
                7.0,
                8.0,
                7.5
        );

        updateStudentRepository.execute(student.getId(), updatedStudent);

        // then
        StudentEntity entity = baseRepository.findById(student.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        assertEquals("Jane Doe", entity.getFullName());
        assertEquals(23, entity.getAge());
        assertEquals(7.0, entity.getFirstSemesterGrade());
        assertEquals(8.0, entity.getSecondSemesterGrade());
        assertEquals(7.5, entity.getFinalGrade());
    }

    @Test
    void given_invalid_student_when_repository_is_executed_then_return_valid_student() {
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
        Student updatedStudent = new Student(
                null,
                23,
                7.0,
                8.0,
                7.5
        );

        updateStudentRepository.execute(student.getId(), updatedStudent);

        // then
        StudentEntity entity = baseRepository.findById(student.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        assertEquals("John Doe", entity.getFullName());
        assertEquals(23, entity.getAge());
        assertEquals(7.0, entity.getFirstSemesterGrade());
        assertEquals(8.0, entity.getSecondSemesterGrade());
        assertEquals(7.5, entity.getFinalGrade());
    }

    @Test
    void given_invalid_id_when_repository_is_executed_then_throw_exception() {
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
        Student updatedStudent = new Student(
                "Jane Doe",
                23,
                7.0,
                8.0,
                7.5
        );

        StudentNotFoundException exception = assertThrows(
                StudentNotFoundException.class,
                () -> updateStudentRepository.execute(student.getId() + 1, updatedStudent)
        );

        // then
        assertEquals("student not found", exception.getMessage());
    }

    @Test
    void given_null_student_when_repository_is_executed_then_throw_exception() {
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
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> updateStudentRepository.execute(student.getId(), null)
        );

        // then
        assertEquals("source object must not be null", exception.getMessage());
    }

}