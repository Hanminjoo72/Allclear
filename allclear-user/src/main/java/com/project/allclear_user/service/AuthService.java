package com.project.allclear_user.service;

import com.project.allclear_user.domain.dto.LoginDto;
import com.project.allclear_user.domain.dto.TokenDto;
import com.project.allclear_user.domain.entity.Student;
import com.project.allclear_user.jwt.JwtGenerator;
import com.project.allclear_user.jwt.JwtProvider;
import com.project.allclear_user.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AuthService {

    final private JwtGenerator jwtGenerator;
    final private JwtProvider jwtProvider;
    final private MemberRepository memberRepository;
    @Transactional
    public TokenDto generateAccessToken(String token) {
        if(!jwtProvider.validateTokenBoolean(token))
            throw new RuntimeException("토큰깂이 없습니다.");
        Long memberId = jwtProvider.getMemberIdFromJwtToken(token);
        String accessToken = jwtGenerator.generateAccessToken(memberId);
        return TokenDto.builder()
                .accessToken(accessToken).build();
    }

    @Transactional
    public LoginDto.ResponseDto generateToken(Student student){
        String newAccessToken = jwtGenerator.generateAccessToken(student.getId());
        String newRefreshToken = jwtGenerator.generateRefreshToken(student.getId());

        student.updateRefreshToken(newRefreshToken);

        return LoginDto.ResponseDto.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }


}
