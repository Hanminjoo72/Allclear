package com.project.allclear_user.controller;

import com.project.allclear_user.domain.dto.LoginDto;
import com.project.allclear_user.domain.dto.SignupDto;
import com.project.allclear_user.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public boolean signUp(@RequestBody SignupDto.RequestDto signupDto){
        return memberService.signUp(signupDto);
    }

    @PostMapping
    public LoginDto.ResponseDto login(@RequestBody LoginDto.RequestDto loginDto){
        return memberService.login(loginDto);
    }

    public boolean changeInfo(@RequestBody SignupDto.RequestDto requestDto){
       memberService.changeInfo(requestDto);

        return true;
    }
}
