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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetStudentByIdControllerTest extends BaseControllerTest {

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
    void given_valid_id_when_getStudentById_then_status_200_OK_and_return_student() throws Exception {
        StudentEntity entity = new StudentEntity(
                null,
                "John Doe",
                18,
                10.0,
                8.0,
                7.0
        );

        entityManager.persistAndFlush(entity);

        MvcResult result = mockMvc.perform(get("/students/{id}/", entity.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        String expectedJsonResponse = """
                        {
                            "age":18,
                            "full_name":"John Doe",
                            "first_semester_grade":10.0,
                            "second_semester_grade":8.0,
                            "final_grade":7.0
                        }
                """;

        JSONAssert.assertEquals(expectedJsonResponse, jsonResponse, false);
    }

    @Test
    void given_invalid_id_when_getStudentById_then_status_404_NOT_FOUND() throws Exception {
        MvcResult result = mockMvc.perform(get("/students/{id}/", 1L))
                .andExpect(status().isNotFound())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        String expectedJsonResponse = """
                        {
                            "message":"student not found",
                            "status":404,
                            "error":"Not Found",
                            "caused_by":"StudentNotFoundException",
                            "timestamp":"2000-01-01T12:00:00"
                        }
                """;

        JSONAssert.assertEquals(expectedJsonResponse, jsonResponse, false);
    }

}
