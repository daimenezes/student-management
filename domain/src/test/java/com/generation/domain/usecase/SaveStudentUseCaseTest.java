package com.generation.domain.usecase;

import com.generation.domain.gateway.SaveStudentGateway;
import com.generation.domain.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class SaveStudentUseCaseTest {

    @InjectMocks
    private SaveStudentUseCase useCase;

    @Mock
    private SaveStudentGateway gateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_valid_student_when_execute_then_save_student_successfully() {
        // given
        Student student = new Student(
                "John Doe",
                18,
                10.0,
                9.5,
                9.75
        );

        doNothing().when(gateway).execute(student);

        // when
        useCase.execute(student);

        // then
        verify(gateway, times(1)).execute(student);
    }

    @Test
    public void given_student_with_null_full_name_when_execute_then_throw_exception() {
        // given
        Student student = new Student(
                null,
                18,
                10.0,
                9.5,
                9.75
        );

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.execute(student));

        // then
        assertEquals("full name is required", exception.getMessage());
    }

    @Test
    public void given_student_with_null_age_when_execute_then_throw_exception() {
        // given
        Student student = new Student(
                "John Doe",
                null,
                10.0,
                9.5,
                9.75
        );

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.execute(student));

        // then
        assertEquals("age is required and must be greater than or equal to 0", exception.getMessage());
    }

    @Test
    public void given_student_with_negative_first_semester_grade_when_execute_then_throw_exception() {
        // given
        Student student = new Student(
                "John Doe",
                18,
                -1.0,
                9.5,
                9.75
        );

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.execute(student));

        // then
        assertEquals("first semester is required and must be between 0 and 10", exception.getMessage());
    }

    @Test
    public void given_student_with_first_semester_grade_greater_than_10_when_execute_then_throw_exception() {
        // given
        Student student = new Student(
                "John Doe",
                18,
                10.1,
                9.5,
                9.75
        );

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.execute(student));

        // then
        assertEquals("first semester is required and must be between 0 and 10", exception.getMessage());
    }

    @Test
    public void given_student_with_negative_second_semester_grade_when_execute_then_throw_exception() {
        // given
        Student student = new Student(
                "John Doe",
                18,
                10.0,
                -1.0,
                9.75
        );

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.execute(student));

        // then
        assertEquals("second semester is required and must be between 0 and 10", exception.getMessage());
    }

    @Test
    public void given_student_with_second_semester_grade_greater_than_10_when_execute_then_throw_exception() {
        // given
        Student student = new Student(
                "John Doe",
                18,
                10.0,
                10.1,
                9.75
        );

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.execute(student));

        // then
        assertEquals("second semester is required and must be between 0 and 10", exception.getMessage());
    }

    @Test
    public void given_student_with_emtpy_full_name_when_execute_then_throw_exception() {
        // given
        Student student = new Student(
                "",
                18,
                10.0,
                9.5,
                9.75
        );

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.execute(student));

        // then
        assertEquals("full name is required", exception.getMessage());
    }

    @Test
    public void given_student_with_age_less_than_0_when_execute_then_throw_exception() {
        // given
        Student student = new Student(
                "John Doe",
                -1,
                10.0,
                9.5,
                9.75
        );

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.execute(student));

        // then
        assertEquals("age is required and must be greater than or equal to 0", exception.getMessage());
    }

    @Test
    public void given_student_with_first_semester_grade_null_when_execute_then_throw_exception() {
        // given
        Student student = new Student(
                "John Doe",
                18,
                null,
                9.5,
                9.5
        );

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.execute(student));

        // then
        assertEquals("first semester is required and must be between 0 and 10", exception.getMessage());
    }

    @Test
    public void given_student_with_second_semester_grade_null_when_execute_then_throw_exception() {
        // given
        Student student = new Student(
                "John Doe",
                18,
                10.0,
                null,
                10.0
        );

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.execute(student));

        // then
        assertEquals("second semester is required and must be between 0 and 10", exception.getMessage());
    }

}