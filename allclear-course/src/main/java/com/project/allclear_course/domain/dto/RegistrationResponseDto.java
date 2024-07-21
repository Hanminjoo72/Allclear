package com.project.allclear_course.domain.dto;

import com.project.allclear_course.domain.entity.Registration;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationResponseDto {
    private final Long id;
    private final String classification;
    private final String courseNumber;
    private final String classNumber;
    private final String name;
    private final String professor;
    private final String lectureDay;
    private final String reenrollment;
    private final String note;
    private final String isFirst;

    public static RegistrationResponseDto from(final Registration registration) {
        return new RegistrationResponseDto(
                registration.getLecture().getId(),
                "전선",
                registration.getLecture().getCourseNumber(),
                registration.getLecture().getClassNumber(),
                "컴퓨터프로그래밍",
                registration.getLecture().getProfessor().getName(),
                registration.getLecture().getLectureDay(),
                "-",
                "",
                ""
        );
    }

}
