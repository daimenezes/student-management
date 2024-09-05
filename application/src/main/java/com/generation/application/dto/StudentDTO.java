package com.generation.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.generation.domain.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {
    @JsonProperty(value = "full_name")
    private String fullName;

    private Integer age;

    @JsonProperty(value = "first_semester_grade")
    private Double firstSemesterGrade;

    @JsonProperty(value = "second_semester_grade")
    private Double secondSemesterGrade;

    @JsonProperty(value = "final_grade")
    private Double finalGrade;

    /**
     * Converts this StudentDTO to a Student model object.
     *
     * @return a Student model object representing this StudentDTO
     */
    public Student toModel() {
        return new Student(
                this.fullName,
                this.age,
                this.firstSemesterGrade,
                this.secondSemesterGrade,
                this.finalGrade
        );
    }

    /**
     * Converts a Student model object to a StudentDTO.
     *
     * @param student the Student model object to be converted
     * @return a StudentDTO representing the given Student model object
     */
    public static StudentDTO fromModel(Student student) {
        return new StudentDTO(
                student.getFullName(),
                student.getAge(),
                student.getFirstSemesterGrade(),
                student.getSecondSemesterGrade(),
                student.getFinalGrade()
        );
    }

    /**
     * Converts a list of Student model objects to a list of StudentDTOs.
     *
     * @param students the list of Student model objects to be converted
     * @return a list of StudentDTOs representing the given list of Student model objects
     */
    public static List<StudentDTO> fromModel(List<Student> students) {
        return students.stream().map(StudentDTO::fromModel).toList();
    }

}
