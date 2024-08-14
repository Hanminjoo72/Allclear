package com.project.allclear_user.service;

import com.project.allclear_user.converter.MemberConverter;
import com.project.allclear_user.domain.dto.LoginDto;
import com.project.allclear_user.domain.dto.ProfileDto;
import com.project.allclear_user.domain.dto.SignupDto;
import com.project.allclear_user.domain.entity.Student;

import com.project.allclear_user.jwt.JwtGenerator;
import com.project.allclear_user.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;


    public boolean signUp(SignupDto.RequestDto signupDto) {
        if(memberRepository.findByLoginId(signupDto.getLoginId()) != null){
            return false;
        }
        String pw = signupDto.getPassword();
        String encodedPw = passwordEncoder.encode(pw);

        Student member = MemberConverter.toEntity(signupDto, encodedPw);
        memberRepository.save(member);

        return true;
    }


    public LoginDto.ResponseDto login(LoginDto.RequestDto loginDto) {
        log.info(loginDto.getPasswd());
        log.info(loginDto.getUserId());

        Student student = memberRepository.findByLoginId(loginDto.getUserId());
        if(student == null){
           return null;
        }
        boolean checkPw = passwordEncoder.matches(loginDto.getPasswd(), student.getPassword());
        if (checkPw) {
            return authService.generateToken(student);
        }
        return null;
    }

    public ProfileDto getProfile(Student student) {
        Student s = memberRepository.findById(student.getId()).orElseThrow();
        return MemberConverter.toDto(s);
    }

    @Transactional
      public Boolean changeInfo(ProfileDto requestDto, Student student) {
        Student s = memberRepository.findById(student.getId()).get();
        if(student == null){
            return false;
        }
        String pw = requestDto.getPassword();
        String encodedPw = passwordEncoder.encode(pw);
        student.updateProfile(requestDto, encodedPw);
        return true;
    }

}
