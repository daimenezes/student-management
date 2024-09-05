package com.generation.application.controller;

import com.generation.application.dto.StudentDTO;
import com.generation.application.handler.DeleteStudentHandler;
import com.generation.application.handler.GetAllStudentsHandler;
import com.generation.application.handler.GetStudentByIdHandler;
import com.generation.application.handler.SaveStudentHandler;
import com.generation.application.handler.UpdateStudentHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final SaveStudentHandler saveStudentHandler;
    private final GetAllStudentsHandler getAllStudentsHandler;
    private final GetStudentByIdHandler getStudentByIdHandler;
    private final UpdateStudentHandler updateStudentHandler;
    private final DeleteStudentHandler deleteStudentHandler;

    @GetMapping("/")
    public ResponseEntity<List<StudentDTO>> getAll() {
        List<StudentDTO> output = getAllStudentsHandler.handle();
        return ResponseEntity.ok(output);
    }

    @GetMapping("/{id}/")
    public ResponseEntity<StudentDTO> getById(@PathVariable Long id) {
        StudentDTO output = getStudentByIdHandler.handle(id);
        return ResponseEntity.ok(output);
    }

    @PostMapping("/")
    public ResponseEntity<Void> save(@RequestBody StudentDTO input) {
        saveStudentHandler.handle(input);
        URI uri = URI.create("/students/");
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}/")
    public ResponseEntity<StudentDTO> update(@PathVariable Long id, @RequestBody StudentDTO input) {
        StudentDTO output = updateStudentHandler.handle(id, input);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteStudentHandler.handle(id);
        return ResponseEntity.ok().build();
    }

}
