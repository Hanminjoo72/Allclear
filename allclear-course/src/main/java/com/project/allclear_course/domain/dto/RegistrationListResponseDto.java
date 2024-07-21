package com.project.allclear_course.domain.dto;

import com.project.allclear_course.domain.entity.Registration;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RegistrationListResponseDto {
    private final List<RegistrationResponseDto> response;

    public static RegistrationListResponseDto from(final List<Registration> registrations) {
        return new RegistrationListResponseDto(
                registrations.stream()
                .map(RegistrationResponseDto::from)
                .toList()
        );
    }
}
