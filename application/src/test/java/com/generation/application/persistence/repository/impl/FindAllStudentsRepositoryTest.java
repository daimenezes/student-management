package com.generation.application.persistence.repository.impl;

import com.generation.application.persistence.entity.StudentEntity;
import com.generation.application.persistence.repository.StudentRepository;
import com.generation.domain.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class FindAllStudentsRepositoryTest {

    @Autowired
    private FindAllStudentsRepository findAllStudentsRepository;

    @Autowired
    private StudentRepository baseRepository;

    @Test
    void given_no_students_when_repository_is_executed_then_return_empty_list() {
        // when
        List<Student> result = findAllStudentsRepository.execute();

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void given_students_when_repository_is_executed_then_return_students_successfully() {
        // given
        List<StudentEntity> entities = List.of(
                new StudentEntity(
                        null,
                        "John Doe",
                        25,
                        8.0,
                        9.0,
                        8.5
                ),
                new StudentEntity(
                        null,
                        "Jane Doe",
                        23,
                        7.0,
                        8.0,
                        7.5
                )
        );

        baseRepository.saveAll(entities);

        // when
        List<Student> result = findAllStudentsRepository.execute();

        // then
        assertEquals(2, result.size());
    }

}