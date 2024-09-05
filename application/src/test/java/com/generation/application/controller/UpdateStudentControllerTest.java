package com.generation.application.controller;

import com.generation.application.persistence.entity.StudentEntity;
import com.generation.application.utils.BaseControllerTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.mockito.Mockito.mockStatic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateStudentControllerTest extends BaseControllerTest {

    private MockedStatic<LocalDateTime> locaDateTimeMocked;

    @BeforeEach
    void setUp() {
        LocalDateTime now = LocalDateTime.of(2000, 1, 1, 12, 0, 0);
        locaDateTimeMocked = mockStatic(LocalDateTime.class);
        locaDateTimeMocked.when(LocalDateTime::now).thenReturn(now);
    }

    @AfterEach
    void tearDown() {
        locaDateTimeMocked.close();
    }

    @Test
    @Transactional
    void given_valid_student_when_updateStudent_then_status_200_OK() throws Exception {
        StudentEntity entity = new StudentEntity(
                null,
                "John Doe",
                25,
                8.0,
                9.0,
                8.5
        );

        entityManager.persistAndFlush(entity);

        String updatedStudent = """
                {
                    "full_name":"Jane Doe",
                    "age":23,
                    "first_semester_grade":7.0,
                    "second_semester_grade":8.0,
                    "final_grade":7.5
                }
                """;

        MvcResult result = mockMvc.perform(put("/students/{id}/", entity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedStudent))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        String expectedJsonResponse = """
                {
                    "full_name":"Jane Doe",
                    "age":23,
                    "first_semester_grade":7.0,
                    "second_semester_grade":8.0,
                    "final_grade":7.5
                }
                """;

        JSONAssert.assertEquals(expectedJsonResponse, jsonResponse, true);
    }

    @Test
    @Transactional
    void given_invalid_student_when_updateStudent_then_status_400_BAD_REQUEST() throws Exception {
        StudentEntity student = new StudentEntity(
                null,
                "John Doe",
                25,
                8.0,
                9.0,
                8.5
        );

        entityManager.persistAndFlush(student);

        String updatedStudent = """
                {
                    "full_name":"Jane Doe",
                    "age":23,
                    "first_semester_grade":7.0,
                    "second_semester_grade":null,
                    "final_grade":7.5
                }
                """;

        MvcResult result = mockMvc.perform(put("/students/{id}/", student.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedStudent))
                .andExpect(status().isBadRequest())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        String expectedJsonResponse = """
                {
                    "message":"second semester grade is required and must be between 0 and 10",
                    "status":400,
                    "error":"Bad Request",
                    "timestamp":"2000-01-01T12:00:00",
                    "caused_by":"BusinessException"
                }
                """;

        JSONAssert.assertEquals(expectedJsonResponse, jsonResponse, false);
    }

    @Test
    void given_invalid_id_when_updateStudent_then_status_400_NOT_FOUND() throws Exception {
        String updatedStudent = """
                {
                    "full_name":"Jane Doe",
                    "age":23,
                    "first_semester_grade":7.0,
                    "second_semester_grade":8.0,
                    "final_grade":7.5
                }
                """;

        mockMvc.perform(put("/students/{id}/", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedStudent))
                .andExpect(status().isNotFound())
                .andReturn();
    }

}
