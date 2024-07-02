package com.project.allclear_user.converter;

import com.project.allclear_user.domain.dto.SignupDto;
import com.project.allclear_user.domain.entity.Student;

import java.lang.reflect.Member;

public class MemberConverter {
    public static Student toEntity(SignupDto.RequestDto requestDto, String encodedPw){
        return Student.builder()
                .grade(requestDto.getGrade())
                .id(requestDto.getLoginId())
                .studentIdNumber(requestDto.getStudentIdNumber())
                .loginId(requestDto.getLoginId())
                .password(encodedPw)
                .build();
    }
}
