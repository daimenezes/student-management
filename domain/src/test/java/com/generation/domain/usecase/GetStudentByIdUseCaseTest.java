package com.generation.domain.usecase;


import com.generation.domain.gateway.GetStudentByIdGateway;
import com.generation.domain.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class GetStudentByIdUseCaseTest {

    @InjectMocks
    private GetStudentByIdUseCase useCase;

    @Mock
    private GetStudentByIdGateway gateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_valid_id_when_execute_then_return_student() {
        // given
        Long id = 1L;
        Optional<Student> student = Optional.of(
                new Student(
                        "John Doe",
                        18,
                        10.0,
                        9.5,
                        9.75
                )
        );

        when(gateway.execute(id)).thenReturn(student);

        // when
        Optional<Student> result = useCase.execute(id);

        // then
        assertEquals(student, result);
    }

    @Test
    public void given_valid_id_when_execute_then_return_empty() {
        // given
        Long id = 1L;
        Optional<Student> student = Optional.empty();

        when(gateway.execute(id)).thenReturn(student);

        // when
        Optional<Student> result = useCase.execute(id);

        // then
        assertEquals(student, result);
    }

    @Test
    public void given_null_id_when_execute_then_throw_exception() {
        // given
        Long id = null;

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.execute(id));

        // then
        assertEquals("id is required and must be greater than 0", exception.getMessage());
    }

    @Test
    public void given_id_less_than_0_when_execute_then_throw_exception() {
        // given
        Long id = -1L;

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.execute(id));

        // then
        assertEquals("id is required and must be greater than 0", exception.getMessage());
    }
}