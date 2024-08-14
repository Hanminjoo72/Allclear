package com.project.allclear_user.converter;

import com.project.allclear_user.domain.dto.ProfileDto;
import com.project.allclear_user.domain.dto.SignupDto;
import com.project.allclear_user.domain.entity.Student;

import java.lang.reflect.Member;

public class MemberConverter {
    public static Student toEntity(SignupDto.RequestDto requestDto, String encodedPw){
        return Student.builder()
                .grade(requestDto.getGrade())
                .studentIdNumber(requestDto.getStudentIdNumber())
                .loginId(requestDto.getLoginId())
                .password(encodedPw)
                .build();
    }

    public static ProfileDto toDto(Student student) {
        return ProfileDto.builder()
                .studentIdNumber(student.getStudentIdNumber())
                .grade(student.getGrade())
                .loginId(student.getLoginId())
                .build();
    }
}
