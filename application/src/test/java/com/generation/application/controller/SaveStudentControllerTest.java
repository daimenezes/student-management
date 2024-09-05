package com.generation.application.controller;

import com.generation.application.utils.BaseControllerTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;

import static org.mockito.Mockito.mockStatic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SaveStudentControllerTest extends BaseControllerTest {

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
    void given_valid_student_when_saveStudent_then_status_201_CREATED() throws Exception {
        String student = """
                {
                    "full_name":"John Doe",
                    "age":18,
                    "first_semester_grade":10.0,
                    "second_semester_grade":8.0,
                    "final_grade":7.0
                }
                """;

        mockMvc.perform(post("/students/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(student))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void given_invalid_student_when_saveStudent_then_status_400_BAD_REQUEST() throws Exception {
        String student = """
                {
                    "full_name":"John Doe",
                    "age":18,
                    "first_semester_grade":10.0,
                    "second_semester_grade":null,
                    "final_grade": 10.0
                }
                """;

        MvcResult result = mockMvc.perform(post("/students/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(student))
                .andExpect(status().isBadRequest())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        String expectedJsonResponse = """
                                   {
                                      "message":"second semester is required and must be between 0 and 10",
                                      "status":400,
                                      "error":"Bad Request",
                                      "timestamp":"2000-01-01T12:00:00",
                                      "caused_by":"BusinessException"
                                   }
                """;

        JSONAssert.assertEquals(expectedJsonResponse, jsonResponse, false);
    }

}
