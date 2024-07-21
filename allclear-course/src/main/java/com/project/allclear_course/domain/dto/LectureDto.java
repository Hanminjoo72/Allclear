package com.project.allclear_course.domain.dto;

import lombok.Data;

@Data
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
}
