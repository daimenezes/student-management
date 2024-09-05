package com.generation.domain.usecase;

import com.generation.domain.gateway.GetAllStudentsGateway;
import com.generation.domain.model.Student;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
public class GetAllStudentsUseCase {

    @Inject
    private final GetAllStudentsGateway gateway;

    public GetAllStudentsUseCase(GetAllStudentsGateway gateway) {
        this.gateway = gateway;
    }

    /**
     * Retrieves all students by delegating the call to the gateway.
     *
     * @return a list of Student model objects representing all students
     */
    public List<Student> execute() {
        return gateway.execute();
    }

}
