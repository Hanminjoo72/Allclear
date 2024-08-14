package com.project.allclear_user.controller;

import com.project.allclear_user.domain.dto.LoginDto;
import com.project.allclear_user.domain.dto.ProfileDto;
import com.project.allclear_user.domain.dto.SignupDto;
import com.project.allclear_user.domain.entity.Student;
import com.project.allclear_user.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/member/")
@AllArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Boolean> signUp(SignupDto.RequestDto signupDto){
        return ResponseEntity.ok(memberService.signUp(signupDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto.ResponseDto> login(LoginDto.RequestDto loginDto){
        return ResponseEntity.ok(memberService.login(loginDto));
    }

    @PatchMapping("/profile")
    public ResponseEntity<Boolean> changeProfile(ProfileDto requestDto, @AuthenticationPrincipal Student student){
       return ResponseEntity.ok(memberService.changeInfo(requestDto, student));
    }

        @GetMapping("/profile")
    public ResponseEntity<ProfileDto> getProfile(Student student){
        return ResponseEntity.ok(memberService.getProfile(student));
    }


}
