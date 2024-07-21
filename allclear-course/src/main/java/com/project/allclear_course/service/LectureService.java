package com.project.allclear_course.service;

import com.project.allclear_course.domain.dto.LectureDto;
import com.project.allclear_course.domain.entity.Lecture;
import com.project.allclear_course.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureService {

    private final LectureRepository lectureRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public Lecture saveLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    public List<Lecture> getAllLecture() {
        return lectureRepository.findAll();
    }



    public List<String> findLectureTitlesByKeyword(String keyword) {
        return lectureRepository.findLectureTitlesByKeyword(keyword);
    }

    public List<String> findLectureTitlesByDepartmentAndGrade(Long departmentId, String grade) {
        List<Lecture> lectures = lectureRepository.findByDepartmentIdAndGrade(departmentId, grade);
        return lectures.stream()
                .map(Lecture::getLectureName)
                .collect(Collectors.toList());
    }
    public List<Lecture>searchByDepartment(Long departmentId, String grade,String lectureName){
        return lectureRepository.searchByDepartment(departmentId, grade,lectureName);
    }
    public List<Lecture>searchByKeyword(String keyword, String lectureName) {
        return lectureRepository.searchByKeyword(keyword, lectureName);
    }

    public List<Lecture> searchByLecture(String lectureCode, String division) {
        return lectureRepository.searchByLectureCode(lectureCode, division);
    }
}
