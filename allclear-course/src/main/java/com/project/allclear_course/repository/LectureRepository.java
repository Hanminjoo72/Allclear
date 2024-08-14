package com.project.allclear_course.repository;

import com.project.allclear_course.domain.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    List<Lecture> findByDepartmentId(Long departmentId);

    List<Lecture> findByGrade(String grade);

    List<Lecture> findByDepartmentIdAndGrade(Long departmentId, String grade);


    //검색유형 - 개설학과
    @Query("SELECT l FROM Lecture l WHERE " +
            "(l.department.id = :departmentId) AND " +
            "(:grade IS NULL OR l.grade = :grade) AND " +
            "(:lectureName IS NULL OR l.lectureName LIKE %:lectureName%)")
    List<Lecture> searchByDepartment(@Param("departmentId") Long departmentId,
                                     @Param("grade") String grade,
                                     @Param("lectureName") String lectureName);

    //검색유형 - 교과목명

    @Query("SELECT l FROM Lecture l WHERE " +
            "(:keyword IS NULL OR l.lectureName LIKE %:keyword%) AND " +
            "(:lectureName IS NULL OR l.lectureName LIKE %:lectureName%)")
    List<Lecture> searchByKeyword(@Param("keyword") String keyword,
                                 @Param("lectureName") String lectureName);

    //학수번호
    @Query("SELECT l FROM Lecture l WHERE " +
            "(:lectureCode IS NULL OR l.lectureCode LIKE %:lectureCode%) AND " +
            "(:division IS NULL OR l.division LIKE %:division%)")
    List<Lecture> searchByLectureCode(@Param("lectureCode") String lectureCode,
                                 @Param("division") String division);

    @Query("SELECT l.lectureName FROM Lecture l WHERE l.lectureName LIKE %:keyword%")
    List<String> findLectureTitlesByKeyword(String keyword);
}
