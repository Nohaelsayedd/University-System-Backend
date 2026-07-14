package com.egabi.University_System_Backend.web.dto;

import lombok.Data;

@Data
public class StudentDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String lvl;
    private Long facultyId;
}
