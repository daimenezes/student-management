package com.generation.application.controller;

import com.generation.application.persistence.entity.StudentEntity;
import com.generation.application.utils.BaseControllerTest;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetAllStudentsControllerTest extends BaseControllerTest {

    @Test
    @Transactional
    void given_nothing_when_getAllStudents_then_status_200_OK_and_values() throws Exception {
        StudentEntity entity = new StudentEntity(
                null,
                "John Doe",
                18,
                10.0,
                8.0,
                7.0
        );

        entityManager.persistAndFlush(entity);

        MvcResult result = mockMvc.perform(get("/students/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        String expectedJsonResponse = """
                [
                    {
                        "age":18,
                        "full_name":"John Doe",
                        "first_semester_grade":10.0,
                        "second_semester_grade":8.0,
                        "final_grade":7.0
                    }
                ]
                """;

        JSONAssert.assertEquals(expectedJsonResponse, jsonResponse, false);
    }

    @Test
    void given_nothing_when_getAllStudents_then_status_200_OK_and_return_empty_list() throws Exception {
        MvcResult result = mockMvc.perform(get("/students/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        String expectedJsonResponse = "[]";

        JSONAssert.assertEquals(expectedJsonResponse, jsonResponse, false);
    }

}
