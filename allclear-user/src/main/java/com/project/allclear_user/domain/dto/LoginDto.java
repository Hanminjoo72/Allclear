package com.project.allclear_user.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class LoginDto {
    @Getter
    @Builder
    @Setter
    public static class RequestDto{
        private String userId;
        private String passwd;
    }

    @Getter
    @Builder
    public static class ResponseDto{
        String refreshToken;
        String accessToken;
    }
}
