package com.egabi.University_System_Backend.web.controller;

import com.egabi.University_System_Backend.service.StudentService;
import com.egabi.University_System_Backend.web.dto.StudentDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public List<StudentDto> getAll() {
        return studentService.getAll();
    }


    @GetMapping("/{id}")
    public StudentDto getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PostMapping
    public StudentDto create(@RequestBody StudentDto studentDto) {
        return studentService.create(studentDto);
    }

    @PutMapping("/{id}")
    public StudentDto update(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        return studentService.update(id, studentDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }
}
