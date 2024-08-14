package com.project.allclear_course.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(force = true)
public class Lecture extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long id;

    private final Long studentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private final Professor professor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private final Department department;

    private final String lectureCode; //학수번호
    private final String division; //분반

    private final String lectureName;

    private final String grade; //학년

    private final int credit;

    private final String lectureDay;

    private final String lectureRoom;

    private final String lectureTime;

    private final int lectureYear;

    private final int semester;

    private final String syllabus;

    @Builder
    public Lecture(Long studentId, Professor professor, Department department, String lectureCode, String division,
                   String lectureName, String grade, int credit, String lectureDay, String lectureRoom,
                   String lectureTime, int lectureYear, int semester, String syllabus) {
        this.studentId = studentId;
        this.professor = professor;
        this.department = department;
        this.lectureCode = lectureCode;
        this.division = division;
        this.lectureName = lectureName;
        this.grade = grade;
        this.credit = credit;
        this.lectureDay = lectureDay;
        this.lectureRoom = lectureRoom;
        this.lectureTime = lectureTime;
        this.lectureYear = lectureYear;
        this.semester = semester;
        this.syllabus = syllabus;
    }
}
