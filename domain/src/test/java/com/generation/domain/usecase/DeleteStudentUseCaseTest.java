package com.generation.domain.usecase;

import com.generation.domain.gateway.DeleteStudentGateway;
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

class DeleteStudentUseCaseTest {

    @InjectMocks
    private DeleteStudentUseCase useCase;

    @Mock
    private DeleteStudentGateway gateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_valid_id_when_execute_then_delete_student_successfully() {
        // given
        Long id = 1L;

        doNothing().when(gateway).execute(id);

        // when
        useCase.execute(id);

        // then
         verify(gateway, times(1)).execute(id);
    }

    @Test
    public void given_null_id_when_execute_then_throw_exception() {
        // given
        Long id = null;

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.execute(id));

        // then
        assertEquals("id cannot be null or less than or equal to 0", exception.getMessage());
    }

    @Test
    public void given_invalid_id_when_execute_then_throw_exception() {
        // given
        Long id = 0L;

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.execute(id));

        // then
        assertEquals("id cannot be null or less than or equal to 0", exception.getMessage());
    }

}