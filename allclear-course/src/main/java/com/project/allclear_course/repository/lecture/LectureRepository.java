package com.project.allclear_course.repository.lecture;

import com.project.allclear_course.domain.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Optional<Lecture> findByCourseNumberAndClassNumber(String courseNumber, String classNumber);
}
