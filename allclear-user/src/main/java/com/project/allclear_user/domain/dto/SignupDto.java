package com.project.allclear_user.domain.dto;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class SignupDto {
    @Builder
    @Getter
    public static class RequestDto{
        private String loginId;
        private String password;
        private String studentIdNumber;
        private int grade;
    }
}
