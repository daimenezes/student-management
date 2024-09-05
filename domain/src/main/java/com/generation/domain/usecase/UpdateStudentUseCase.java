package com.generation.domain.usecase;

import com.generation.domain.exception.BusinessException;
import com.generation.domain.gateway.UpdateStudentGateway;
import com.generation.domain.model.Student;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class UpdateStudentUseCase {

    private final UpdateStudentGateway gateway;
    private final Logger logger = LoggerFactory.getLogger(UpdateStudentUseCase.class);

    public UpdateStudentUseCase(UpdateStudentGateway gateway) {
        this.gateway = gateway;
    }

    /**
     * Executes the process of updating a student.
     *
     * @param id      the ID of the student to be updated
     * @param student the Student model object containing updated information
     * @return the updated Student model object
     * @throws BusinessException if the student validation fails
     */
    public Student execute(Long id, Student student) {
        validate(student);
        return gateway.execute(id, student);
    }

    private void validate(Student student) {
        logger.info("validating student: {}", student);

        if (student.getFullName() == null || student.getFullName().isEmpty()) {
            throw new BusinessException("full name is required");
        }

        if (student.getAge() == null || student.getAge() < 0) {
            throw new BusinessException("age is required and must be greater than or equal to 0");
        }

        if (student.getFirstSemesterGrade() == null || student.getFirstSemesterGrade() < 0 || student.getFirstSemesterGrade() > 10) {
            throw new BusinessException("first semester grade is required and must be between 0 and 10");
        }

        if (student.getSecondSemesterGrade() == null || student.getSecondSemesterGrade() < 0 || student.getSecondSemesterGrade() > 10) {
            throw new BusinessException("second semester grade is required and must be between 0 and 10");
        }
    }

}
