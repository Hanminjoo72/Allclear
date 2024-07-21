package com.project.allclear_course.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationRequestDto {
    private String courseNumber;
    private String classNumber;
}
