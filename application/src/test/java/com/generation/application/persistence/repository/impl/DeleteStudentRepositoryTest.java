package com.generation.application.persistence.repository.impl;

import com.generation.application.persistence.entity.StudentEntity;
import com.generation.application.persistence.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class DeleteStudentRepositoryTest {

    @Autowired
    private DeleteStudentRepository deleteStudentRepository;

    @Autowired
    private StudentRepository baseRepository;

    @Test
    void given_valid_id_when_repository_is_executed_then_delete_student_successfully() {
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
        deleteStudentRepository.execute(student.getId());

        // then
        assertTrue(baseRepository.findById(student.getId()).isEmpty());
    }

    @Test
    void given_invalid_id_when_repository_is_executed_then_return_valid_student() {
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
        deleteStudentRepository.execute(student.getId() + 1);

        // then
        assertTrue(baseRepository.findById(student.getId()).isPresent());
    }

}