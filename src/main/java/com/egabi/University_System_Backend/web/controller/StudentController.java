package com.egabi.University_System_Backend.web.controller;

import com.egabi.University_System_Backend.model.Faculty;
import com.egabi.University_System_Backend.model.Student;
import com.egabi.University_System_Backend.repository.FacultyRepository;
import com.egabi.University_System_Backend.repository.StudentRepository;
import com.egabi.University_System_Backend.web.dto.StudentDto;
import com.egabi.University_System_Backend.web.mapper.StudentMapper;
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
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final StudentMapper studentMapper;

    public StudentController(StudentRepository studentRepository,
                              FacultyRepository facultyRepository,
                              StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.studentMapper = studentMapper;
    }

    @GetMapping
    public List<StudentDto> getAll() {
        return studentMapper.mapList(studentRepository.findAll());
    }

    @GetMapping("/{id}")
    public StudentDto getById(@PathVariable Long id) {
        return studentMapper.map(studentRepository.findById(id).orElseThrow());
    }

    @PostMapping
    public StudentDto create(@RequestBody StudentDto studentDto) {
        Student student = studentMapper.unmap(studentDto);
        student.setFaculty(getFaculty(studentDto.getFacultyId()));
        return studentMapper.map(studentRepository.save(student));
    }

    @PutMapping("/{id}")
    public StudentDto update(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        Student student = studentMapper.unmap(studentDto);
        student.setId(id);
        student.setFaculty(getFaculty(studentDto.getFacultyId()));
        return studentMapper.map(studentRepository.save(student));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }

    private Faculty getFaculty(Long facultyId) {
        return facultyRepository.findById(facultyId).orElseThrow();
    }
}
