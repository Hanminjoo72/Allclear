package com.project.allclear_user.service;

import com.project.allclear_user.converter.MemberConverter;
import com.project.allclear_user.domain.dto.LoginDto;
import com.project.allclear_user.domain.dto.SignupDto;
import com.project.allclear_user.domain.entity.Student;
import com.project.allclear_user.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    public boolean signUp(SignupDto.RequestDto signupDto) {
        String pw = signupDto.getPassword();
        String encodedPw = passwordEncoder.encode(pw);

        Student member = MemberConverter.toEntity(signupDto, encodedPw);
        memberRepository.save(member);
        return true;
    }


    public LoginDto.ResponseDto login(LoginDto.RequestDto loginDto) {
        Student student = memberRepository.findByLoginId(loginDto.getId());
        
        boolean checkPw = passwordEncoder.matches(loginDto.getPw(), student.getPassword());
//        if(checkPw){
//            //jwt 발급
//            return ;
//        }
        return null;
    }

    public void changeInfo(SignupDto.RequestDto requestDto) {
        //Student student = memberRepository.findById()
        Student s;
        String pw = requestDto.getPassword();
        String encodedPw = passwordEncoder.encode(pw);
        Student newStudent = MemberConverter.toEntity(requestDto, encodedPw);
        memberRepository.save(newStudent);
    }
}
