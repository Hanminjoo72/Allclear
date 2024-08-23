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
    private final int credit;
    private final String professor;
    private final String lectureDay;
    private final String reenrollment;
    private final String note;
    private final String isFirst;

    public static RegistrationResponseDto from(final Registration registration) {
        return new RegistrationResponseDto(
                registration.getLecture().getId(),
                "전선",
                registration.getLecture().getLectureCode(),
                registration.getLecture().getDivision(),
                registration.getLecture().getLectureName(),
                registration.getLecture().getCredit(),
                registration.getLecture().getProfessor().getName(),
                registration.getLecture().getLectureDay(),
                "-",
                "",
                ""
        );
    }

}
