package com.project.allclear_course.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(force = true)
@Table(schema = "lecture")
public class Lecture extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long id; //기본키

//    private final Long studentId; //필요없음

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private  Professor professor; //교수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private  Department department; //학과

    private  String lectureCode; //학수번호
    private  String division; //분반

    private  String lectureName;  //강의 이름

    private  String grade; //학년

    private  int credit; //학점
    private int allowedNumberOfStudents; //수강 가능 인원
    private int currentNumberOfStudents;//현재 수강 인원

    private  String lectureDay; //강의 시간

    private  String lectureRoom; //강의 시간

    private  String lectureTime; //강의 시간

    private  int lectureYear; //강의 년도

    private  int semester; //학기

    private  String syllabus; //강의 계획서

    @Builder
    public Lecture (Long id, Professor professor, Department department, String lectureCode, String division,
                   String lectureName, String grade, int credit, int allowedNumberOfStudents, int currentNumberOfStudents, String lectureDay, String lectureRoom,
                   String lectureTime, int lectureYear, int semester, String syllabus, boolean delStatus) {
//        this.studentId = studentId;
        this.id = id;
        this.professor = professor;
        this.department = department;
        this.lectureCode = lectureCode;
        this.division = division;
        this.lectureName = lectureName;
        this.grade = grade;
        this.credit = credit;
        this.allowedNumberOfStudents = allowedNumberOfStudents;
        this.currentNumberOfStudents = currentNumberOfStudents;
        this.lectureDay = lectureDay;
        this.lectureRoom = lectureRoom;
        this.lectureTime = lectureTime;
        this.lectureYear = lectureYear;
        this.semester = semester;
        this.syllabus = syllabus;
        this.delStatus = delStatus;
    }
}
