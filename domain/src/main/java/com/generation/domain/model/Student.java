package com.generation.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    private String fullName;
    private Integer age;
    private Double firstSemesterGrade;
    private Double secondSemesterGrade;
    private Double finalGrade;
}
