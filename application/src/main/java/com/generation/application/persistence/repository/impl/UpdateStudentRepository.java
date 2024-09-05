package com.generation.application.persistence.repository.impl;

import com.generation.application.exception.StudentNotFoundException;
import com.generation.application.persistence.entity.StudentEntity;
import com.generation.application.persistence.repository.StudentRepository;
import com.generation.domain.gateway.UpdateStudentGateway;
import com.generation.domain.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class UpdateStudentRepository implements UpdateStudentGateway {

    private final StudentRepository repository;

    /**
     * Updates an existing student by their ID.
     *
     * @param id         the ID of the student to be updated
     * @param newStudent the Student object containing the updated student data
     * @return a Student object representing the updated student
     * @throws StudentNotFoundException if the student with the given ID is not found
     */
    @Override
    public Student execute(Long id, Student newStudent) {
        StudentEntity oldStudent = repository.findById(id).orElseThrow(StudentNotFoundException::new);
        copyNonNullProperties(newStudent, oldStudent);
        StudentEntity output = repository.save(oldStudent);
        return output.toModel();
    }

    private void copyNonNullProperties(Student source, StudentEntity target) {
        if (source == null) throw new IllegalArgumentException("source object must not be null");

        BeanWrapper src = new BeanWrapperImpl(source);
        BeanWrapper trg = new BeanWrapperImpl(target);

        for (Field property : target.getClass().getDeclaredFields()) {
            String propertyName = property.getName();

            if ("id".equals(propertyName)) continue;

            Object providedObject = src.getPropertyValue(propertyName);
            if (providedObject != null && !(providedObject instanceof Collection<?>)) {
                trg.setPropertyValue(propertyName, providedObject);
            }
        }
    }

}
