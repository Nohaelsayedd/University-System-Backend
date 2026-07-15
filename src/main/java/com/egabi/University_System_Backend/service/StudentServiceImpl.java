package com.egabi.University_System_Backend.service;

import com.egabi.University_System_Backend.model.Faculty;
import com.egabi.University_System_Backend.model.Student;
import com.egabi.University_System_Backend.repository.FacultyRepository;
import com.egabi.University_System_Backend.repository.StudentRepository;
import com.egabi.University_System_Backend.web.dto.StudentDto;
import com.egabi.University_System_Backend.web.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository,
                               FacultyRepository facultyRepository,
                               StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<StudentDto> getAll() {
        return studentMapper.mapList(studentRepository.findAll());
    }

    @Override
    public StudentDto getById(Long id) {
        return studentMapper.map(findStudent(id));
    }

    @Override
    public StudentDto create(StudentDto studentDto) {
        validate(studentDto);

        if (studentRepository.existsByEmail(studentDto.getEmail())) {
            throw new RuntimeException("Email already exists: " + studentDto.getEmail());
        }

        Student student = studentMapper.unmap(studentDto);
        student.setFaculty(findFaculty(studentDto.getFacultyId()));
        return studentMapper.map(studentRepository.save(student));
    }

    @Override
    public StudentDto update(Long id, StudentDto studentDto) {
        validate(studentDto);
        findStudent(id);

        if (studentRepository.existsByEmailAndIdNot(studentDto.getEmail(), id)) {
            throw new RuntimeException("Email already exists: " + studentDto.getEmail());
        }

        Student student = studentMapper.unmap(studentDto);
        student.setId(id);
        student.setFaculty(findFaculty(studentDto.getFacultyId()));
        return studentMapper.map(studentRepository.save(student));
    }

    private void validate(StudentDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto.getFirstName() == null || dto.getFirstName().isBlank()) {
            errors.add("First name is required");
        }
        if (dto.getLastName() == null || dto.getLastName().isBlank()) {
            errors.add("Last name is required");
        }
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            errors.add("Email is required");
        } else if (!dto.getEmail().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            errors.add("Email must be a valid email address");
        }
        if (dto.getLvl() == null || dto.getLvl().isBlank()) {
            errors.add("Level is required");
        } else if (!dto.getLvl().matches("[1-5]")) {
            errors.add("Level must be between 1 and 5");
        }
        if (dto.getFacultyId() == null) {
            errors.add("Faculty id is required");
        }

        if (!errors.isEmpty()) {
            throw new RuntimeException(String.join("; ", errors));
        }
    }

    @Override
    public void delete(Long id) {
        findStudent(id);
        studentRepository.deleteById(id);
    }

    private Student findStudent(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    private Faculty findFaculty(Long facultyId) {
        return facultyRepository.findById(facultyId).orElseThrow();
    }
}
