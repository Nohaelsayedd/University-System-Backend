package com.egabi.University_System_Backend.service;

import com.egabi.University_System_Backend.web.dto.StudentDto;

import java.util.List;
public interface StudentService {

    List<StudentDto> getAll();

    StudentDto getById(Long id);

    StudentDto create(StudentDto studentDto);

    StudentDto update(Long id, StudentDto studentDto);

    void delete(Long id);
}
