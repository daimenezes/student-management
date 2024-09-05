package com.generation.domain.usecase;

import com.generation.domain.gateway.GetAllStudentsGateway;
import com.generation.domain.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetAllStudentsUseCaseTest {

    @InjectMocks
    private GetAllStudentsUseCase useCase;

    @Mock
    private GetAllStudentsGateway gateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void when_execute_then_get_all_students() {
        // given
        List<Student> students = List.of(new Student(), new Student());

        when(gateway.execute()).thenReturn(students);

        // when
        List<Student> result = useCase.execute();

        assertEquals(2, result.size());

        // then
        verify(gateway, times(1)).execute();
    }

}