package com.generation.application.controller;

import com.generation.application.persistence.entity.StudentEntity;
import com.generation.application.utils.BaseControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteStudentControllerTest extends BaseControllerTest {

    @Test
    @Transactional
    void given_valid_id_when_deleteStudent_then_status_200_OK() throws Exception {
        StudentEntity student = new StudentEntity(
                null,
                "John Doe",
                18,
                10.0,
                8.0,
                7.0
        );

        entityManager.persistAndFlush(student);

        mockMvc.perform(delete("/students/{id}/", student.getId()))
                .andExpect(status().isOk())
                .andReturn();
    }

}
