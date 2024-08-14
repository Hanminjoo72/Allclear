package com.project.allclear_course.form;

public class LectureSearchForm {

    private Long departmentId;
    private String lectureName;
    private String grade;


    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getCourseName() {
        return lectureName;
    }

    public void setCourseName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
