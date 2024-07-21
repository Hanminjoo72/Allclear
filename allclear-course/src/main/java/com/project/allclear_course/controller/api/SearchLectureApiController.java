package com.project.allclear_course.controller.api;

import com.project.allclear_course.domain.entity.Department;
import com.project.allclear_course.domain.entity.Lecture;
import com.project.allclear_course.form.LectureSearchForm;
import com.project.allclear_course.service.DepartmentService;
import com.project.allclear_course.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lectures/api")
public class SearchLectureApiController {

    @Autowired
    private LectureService lectureService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Lecture>> getAllCourses() {
        List<Lecture> courses = lectureService.getAllLecture();
        return ResponseEntity.ok(courses);
    }

    //개설학과 옵션 검색
    @GetMapping("/search")
    public ResponseEntity<List<Lecture>> searchCourses(
            @RequestParam Long departmentId,
            @RequestParam String grade,
            @RequestParam String lectureName) {
        List<Lecture> courses = lectureService.searchByDepartment(departmentId, grade, lectureName);
        return ResponseEntity.ok(courses);
    }
    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getLectureTitles() {
        List<Department> departments =  departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    //개설학과 옵션으로 검색할 때 학과(전공)과 학년을 검색을 선택했을 때 조건에 부합하는 강의를 옵션에 보여주는 컨트롤러
    @GetMapping("/titles")
    public ResponseEntity<List<String>> getLectureTitles(@RequestParam Long departmentId, @RequestParam String grade) {
        List<String> titles = lectureService.findLectureTitlesByDepartmentAndGrade(departmentId, grade);
        return ResponseEntity.ok(titles);
    }

    //교과목명 옵션 검색어 입력 시
    @GetMapping("/lectureTitle")
    public ResponseEntity<List<String>> getTitles(@RequestParam String keyword) {
        List<String> titles = lectureService.findLectureTitlesByKeyword(keyword);
        return ResponseEntity.ok(titles);
    }


}