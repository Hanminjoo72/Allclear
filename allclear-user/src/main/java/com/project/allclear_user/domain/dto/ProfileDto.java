package com.project.allclear_user.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProfileDto {
        private String loginId;
        private String studentIdNumber;
        private String password;
        private int grade;
}
