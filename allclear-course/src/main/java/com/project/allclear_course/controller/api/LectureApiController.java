package com.project.allclear_course.controller.api;

import com.project.allclear_course.domain.entity.Department;
import com.project.allclear_course.domain.entity.Lecture;
import com.project.allclear_course.domain.entity.Professor;
import com.project.allclear_course.domain.dto.LectureDto;
import com.project.allclear_course.repository.DepartmentRepository;
import com.project.allclear_course.repository.ProfessorRepository;
import com.project.allclear_course.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LectureApiController {

    private final LectureService lectureService;
    private final ProfessorRepository professorRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public LectureApiController(LectureService lectureService, ProfessorRepository professorRepository, DepartmentRepository departmentRepository) {
        this.lectureService = lectureService;
        this.professorRepository = professorRepository;
        this.departmentRepository = departmentRepository;
    }

    @PostMapping("/lectures")
    public Lecture createLecture(@RequestBody LectureDto lectureDto) {

        Professor professor;
        if (lectureDto.getProfessorId() != null) {
            professor = professorRepository.findById(lectureDto.getProfessorId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid professor ID"));
        } else {
            professor = new Professor();
            professor.setId(lectureDto.getProfessorId()); // Set professor's name from DTO
            // Set other properties of professor as needed
            professor = professorRepository.save(professor); // Save professor to generate ID
        }

        Department department;
        if (lectureDto.getDepartmentId() != null) {
            department = departmentRepository.findById(lectureDto.getDepartmentId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid department ID"));
        } else {
            department = new Department();
            department.setId(lectureDto.getDepartmentId()); // Set department's name from DTO
            // Set other properties of department as needed
            department = departmentRepository.save(department); // Save department to generate ID
        }

        Lecture lecture = Lecture.builder()
                .professor(professor)
                .department(department)
                .lectureCode(lectureDto.getLectureCode())
                .division(String.valueOf(lectureDto.getDivision()))
                .lectureName(lectureDto.getLectureName())
                .grade(lectureDto.getGrade())
                .credit(lectureDto.getCredit())
                .lectureDay(lectureDto.getLectureDay())
                .lectureRoom(lectureDto.getLectureRoom())
                .lectureTime(lectureDto.getLectureTime())
                .lectureYear(lectureDto.getLectureYear())
                .semester(lectureDto.getSemester())
                .syllabus(lectureDto.getSyllabus())
                .build();


        return lectureService.saveLecture(lecture);
    }


}
