package com.generation.application.persistence.entity;

import com.generation.domain.model.Student;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "first_semester_grade", nullable = false)
    private Double firstSemesterGrade;

    @Column(name = "second_semester_grade", nullable = false)
    private Double secondSemesterGrade;

    @Column(name = "final_grade", nullable = false)
    private Double finalGrade;

    /**
     * Converts this StudentEntity object to a Student model object.
     *
     * @return a Student model object representing this StudentEntity
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
     * Converts a Student model object to a StudentEntity object.
     *
     * @param student the Student model object to be converted
     * @return a StudentEntity object representing the student
     */
    public static StudentEntity fromModel(Student student) {
        return new StudentEntity(
                0L,
                student.getFullName(),
                student.getAge(),
                student.getFirstSemesterGrade(),
                student.getSecondSemesterGrade(),
                student.getFinalGrade()
        );
    }

}
