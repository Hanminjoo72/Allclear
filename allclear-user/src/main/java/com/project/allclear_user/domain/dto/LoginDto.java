package com.project.allclear_user.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginDto {
    @Getter
    @Builder
    public static class RequestDto{
        Long id;
        String pw;
    }

    @Getter
    @Builder
    public static class ResponseDto{

    }
}
