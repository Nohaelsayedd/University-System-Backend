package com.egabi.University_System_Backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "grades")
@Data
public class Grade {

    @EmbeddedId
    //tells jpa that pk is composite not a single column
    private GradeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseId") //ba2olo en howa howa el fk w el embedded , so avoid duplication
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private Integer grade;

    @Embeddable
    @Data
    public static class GradeId implements Serializable {
        private Long studentId;
        private Long courseId;
    }
}
