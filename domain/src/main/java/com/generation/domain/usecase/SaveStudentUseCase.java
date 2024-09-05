package com.generation.domain.usecase;

import com.generation.domain.exception.BusinessException;
import com.generation.domain.gateway.SaveStudentGateway;
import com.generation.domain.model.Student;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@Named
public class SaveStudentUseCase {

    @Inject
    private final SaveStudentGateway gateway;
    private final Logger logger = LoggerFactory.getLogger(SaveStudentUseCase.class);

    public SaveStudentUseCase(SaveStudentGateway gateway) {
        this.gateway = gateway;
    }

    /**
     * Executes the process of saving a student.
     *
     * @param student the Student model object to be saved
     * @throws BusinessException if the student validation fails
     */
    public void execute(Student student) {
        validate(student);
        gateway.execute(student);
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
            throw new BusinessException("first semester is required and must be between 0 and 10");
        }

        if (student.getSecondSemesterGrade() == null || student.getSecondSemesterGrade() < 0 || student.getSecondSemesterGrade() > 10) {
            throw new BusinessException("second semester is required and must be between 0 and 10");
        }
    }

}
