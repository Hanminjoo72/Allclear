package com.project.allclear_course.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class LectureDto {
    private Long studentId;
    private Long professorId;
    private Long departmentId;
    private String lectureCode;
    private int division;
    private String lectureName;
    private String grade;
    private int credit;
    private String lectureDay;
    private String lectureRoom;
    private String lectureTime;
    private int lectureYear;
    private int semester;
    private String syllabus;

    public LectureDto(Long studentId, Long professorId, Long departmentId, String lectureCode, int division,
                      String lectureName, String grade, int credit, String lectureDay, String lectureRoom,
                      String lectureTime, int lectureYear, int semester, String syllabus) {
        this.studentId = studentId;
        this.professorId = professorId;
        this.departmentId = departmentId;
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
