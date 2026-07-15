package com.egabi.University_System_Backend.web.dto;

import lombok.Data;
// de el hagat bas el user can see and deal with the rest is not public
//clients can only send/receive exactly these fields, in this shape
@Data
public class StudentDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String lvl;
    private Long facultyId;
}
