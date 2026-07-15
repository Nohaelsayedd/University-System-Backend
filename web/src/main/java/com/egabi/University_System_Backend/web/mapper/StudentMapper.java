package com.egabi.University_System_Backend.web.mapper;

import com.egabi.University_System_Backend.model.Student;
import com.egabi.University_System_Backend.web.dto.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "faculty.id", target = "facultyId")
    StudentDto map(Student student);

    @Mapping(target = "faculty", ignore = true)
    Student unmap(StudentDto studentDto);

    List<StudentDto> mapList(List<Student> students);

    List<Student> unmapList(List<StudentDto> studentDtos);
}
