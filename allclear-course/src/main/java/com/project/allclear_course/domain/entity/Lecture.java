package com.project.allclear_course.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lecture extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long id;

    private Long studentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    private String lectureCode; //학수번호
    private String division; //분반

    private String lectureName;

    private String grade; //학년

    private int credit;

    private String lectureDay;

    private String lectureRoom;

    private String lectureTime;

    private int lectureYear;

    private int semester;

    private String syllabus;
}
