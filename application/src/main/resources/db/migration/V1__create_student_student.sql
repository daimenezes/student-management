CREATE TABLE tb_student(
    id                    BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name             VARCHAR(100)  NOT NULL,
    age                   INT           NOT NULL,
    first_semester_grade  DECIMAL(3, 1) NOT NULL,
    second_semester_grade DECIMAL(3, 1) NOT NULL,
    final_grade           DECIMAL(3, 1) NOT NULL
);