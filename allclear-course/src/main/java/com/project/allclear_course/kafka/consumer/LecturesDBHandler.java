package com.project.allclear_course.kafka.consumer;

import com.project.allclear_course.domain.entity.Department;
import com.project.allclear_course.domain.entity.Lecture;
import com.project.allclear_course.domain.entity.Professor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public class LecturesDBHandler {
    public static final Logger logger = LoggerFactory.getLogger(LecturesDBHandler.class.getName());
    private Connection connection = null;
    private PreparedStatement insertPrepared = null;

    // Lecture 테이블에 데이터 삽입하기 위한 SQL 쿼리
    private static final String INSERT_LECTURE_SQL = "INSERT INTO lecture.lecture " +
            "(lecture_id, department_id, lecture_code, lecture_name, division, " +
            " professor_id, credit, grade, lecture_day, lecture_time, " +
            " lecture_year, semester, syllabus, del_status) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    //파일이 바뀌었을 때 해당 아이디를 가진 강의가 있는지 확인하는 매서드
    public boolean lectureExists(Long lectureId) {
        String checkSql = "SELECT COUNT(*) FROM lecture.lecture WHERE lecture_id = ?";
        try (PreparedStatement checkPrepared = connection.prepareStatement(checkSql)) {
            checkPrepared.setLong(1, lectureId);
            try (ResultSet rs = checkPrepared.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            logger.error("Error checking if lecture exists: {}", e.getMessage(), e);
        }
        return false;
    }
    public LecturesDBHandler(String url, String user, String password) {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
            this.insertPrepared = this.connection.prepareStatement(INSERT_LECTURE_SQL);
        } catch(SQLException e) {
            logger.error(e.getMessage());
        }
    }
    public void insertLecture(Lecture lecture) {
        try {
            // Use the prepared statement for a single lecture
            PreparedStatement pstmt = this.connection.prepareStatement(INSERT_LECTURE_SQL);
            pstmt.setLong(1, lecture.getId()); // 강의 아이디
            pstmt.setLong(2, lecture.getDepartment() != null ? lecture.getDepartment().getId() : Types.NULL); // 학과 아이디
            pstmt.setString(3, lecture.getLectureCode()); // 학수번호
            pstmt.setString(4, lecture.getLectureName()); // 강의 제목
            pstmt.setString(5, lecture.getDivision()); // 분반
            pstmt.setLong(6, lecture.getProfessor() != null ? lecture.getProfessor().getId() : Types.NULL); // 교수 아이디
            pstmt.setInt(7, lecture.getCredit()); // 학점
            pstmt.setString(8, lecture.getGrade()); // 이수필수여부
            pstmt.setString(9, lecture.getLectureDay()); // 강의실 및 시간
            pstmt.setString(10, lecture.getLectureTime()); // 수업시간
            pstmt.setInt(11, lecture.getLectureYear()); // 수업년도
            pstmt.setInt(12, lecture.getSemester()); // 학기
            pstmt.setString(13, lecture.getSyllabus()); // 강의 계획서
            pstmt.setBoolean(14, lecture.isDelStatus()); // del_status 값 추가

            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting lecture: {}", e.getMessage(), e);
        }
    }

    public void insertOrUpdateLecture(Lecture lecture) {
        String updateSql = "UPDATE lecture.lecture SET department_id = ?, lecture_code = ?, lecture_name = ?, " +
                "division = ?, professor_id = ?, credit = ?, grade = ?, lecture_day = ?, lecture_time = ?, " +
                "lecture_year = ?, semester = ?, syllabus = ?, del_status = ? WHERE lecture_id = ?";

        if (lectureExists(lecture.getId())) {
            // Lecture exists, perform an update
            try (PreparedStatement pstmt = connection.prepareStatement(updateSql)) {
                pstmt.setLong(1, lecture.getDepartment() != null ? lecture.getDepartment().getId() : Types.NULL);
                pstmt.setString(2, lecture.getLectureCode());
                pstmt.setString(3, lecture.getLectureName());
                pstmt.setString(4, lecture.getDivision());
                pstmt.setLong(5, lecture.getProfessor() != null ? lecture.getProfessor().getId() : Types.NULL);
                pstmt.setInt(6, lecture.getCredit());
                pstmt.setString(7, lecture.getGrade());
                pstmt.setString(8, lecture.getLectureDay());
                pstmt.setString(9, lecture.getLectureTime());
                pstmt.setInt(10, lecture.getLectureYear());
                pstmt.setInt(11, lecture.getSemester());
                pstmt.setString(12, lecture.getSyllabus());
                pstmt.setBoolean(13, lecture.isDelStatus());
                pstmt.setLong(14, lecture.getId());

                pstmt.executeUpdate();
            } catch (SQLException e) {
                logger.error("Error updating lecture: {}", e.getMessage(), e);
            }
        } else {
            // Lecture does not exist, perform an insert
            insertLecture(lecture);
        }
    }

    public void insertLectures(List<Lecture> lectures) {
        try {
            // Disable auto-commit for batch processing
            connection.setAutoCommit(false);

            for (Lecture lecture : lectures) {
                insertPrepared.setLong(1, lecture.getId()); // 강의 아이디
                insertPrepared.setLong(2, lecture.getDepartment() != null ? lecture.getDepartment().getId() : Types.NULL); // 학과 아이디
                insertPrepared.setString(3, lecture.getLectureCode()); // 학수번호
                insertPrepared.setString(4, lecture.getLectureName()); // 강의 제목
                insertPrepared.setString(5, lecture.getDivision()); // 분반
                insertPrepared.setLong(6, lecture.getProfessor() != null ? lecture.getProfessor().getId() : Types.NULL); // 교수 아이디
                insertPrepared.setInt(7, lecture.getCredit()); // 학점
                insertPrepared.setString(8, lecture.getGrade()); // 이수필수여부
                insertPrepared.setString(9, lecture.getLectureDay()); // 강의실 및 시간
                insertPrepared.setString(10, lecture.getLectureTime()); // 수업시간
                insertPrepared.setInt(11, lecture.getLectureYear()); // 수업년도
                insertPrepared.setInt(12, lecture.getSemester()); // 학기
                insertPrepared.setString(13, lecture.getSyllabus()); // 강의 계획서
                insertPrepared.setBoolean(14, lecture.isDelStatus()); // del_status 값 추가
                insertPrepared.addBatch(); // Add to batch
            }
            insertPrepared.executeBatch(); // Execute batch
            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            logger.error("Error inserting lectures batch: {}", e.getMessage(), e);
            try {
                connection.rollback(); // Rollback transaction in case of error
            } catch (SQLException rollbackEx) {
                logger.error("Error during rollback: {}", rollbackEx.getMessage(), rollbackEx);
            }
        } finally {
            try {
                connection.setAutoCommit(true); // Reset auto-commit
            } catch (SQLException e) {
                logger.error("Error resetting auto-commit: {}", e.getMessage(), e);
            }
        }
    }
    public void close() {
        try {
            logger.info("###### LecturesDBHandler is closing");
            if (this.insertPrepared != null) this.insertPrepared.close();
            if (this.connection != null) this.connection.close();
        } catch(SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String url = "jdbc:postgresql://allclear-db.cjg6o2myyjuv.ap-northeast-2.rds.amazonaws.com:5432/allclear";
        String user = "allclear";
        String password = "allclear321!!";
        LecturesDBHandler lecturesDBHandler = new LecturesDBHandler(url, user, password);

        // Lecture 객체 예시
        Lecture lecture = Lecture.builder()
                .id(1L)  // lecture_id
                .department(new Department(1L))  // department_id
                .lectureCode("CS101")  // lecture_code
                .lectureName("Introduction to Computer Science")  // lecture_name
                .division("A")  // division
                .professor(new Professor(1L))  // professor_id
                .credit(3)  // credit
                .grade("1")  // grade
                .lectureDay("Monday")  // lecture_day
                .lectureTime("09:00-10:30")  // lecture_time
                .lectureYear(2024)  // lecture_year
                .semester(1)  // semester
                .syllabus("Course syllabus details")  // syllabus
                .build();

        // 단일 Lecture 객체 삽입
        lecturesDBHandler.insertLecture(lecture);
        lecturesDBHandler.close();
    }
}
