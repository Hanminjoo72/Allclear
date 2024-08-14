package com.project.allclear_course.service;

import com.project.allclear_course.domain.entity.Lecture;
import java.util.List;
public interface LectureService {
    List<Lecture> getAllLectures();
    Lecture getLectureById(Long id);
    void addToWishlist(Long lectureId, Long studentId);
}

