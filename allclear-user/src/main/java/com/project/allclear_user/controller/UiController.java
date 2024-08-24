package com.project.allclear_user.controller;

import com.project.allclear_user.domain.dto.LoginDto;
import com.project.allclear_user.domain.dto.ProfileDto;
import com.project.allclear_user.domain.dto.SignupDto;
import com.project.allclear_user.domain.entity.Student;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
@Slf4j
public class UiController {

    private final MemberController memberController;

    @GetMapping("login-page")
    public String loginUi(){
        return "/member/login";
    }


    @GetMapping("signup-page")
    public String signUi(){
        return "/member/signup";
    }

    @GetMapping("profile-page")
    public String profileUi(){
        return "/member/profile";
    }

    @PostMapping("/login")
    public String login(LoginDto.RequestDto loginDto, Model model){
        LoginDto.ResponseDto responseDto = memberController.login(loginDto).getBody();
        if (responseDto != null) {
            model.addAttribute("user", responseDto);
            return "/board/home";
        } else {
            model.addAttribute("error", "존재하지 않는 ID와 비밀번호 입니다.");
            return "/member/login";
        }
    }

    @PostMapping("/signup")
    public String signup(SignupDto.RequestDto signupDto, Model model){
        Boolean result = memberController.signUp(signupDto).getBody();
        if(result){
            model.addAttribute("result", "회원가입이 완료되었습니다.");
        }
        else{
            model.addAttribute("result", "이미 존재하는 회원입니다.");
        }
        return "signup";
    }

    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal Student student, Model model){
        ProfileDto profileDto = memberController.getProfile(student).getBody();
        model.addAttribute("profile", profileDto);
        log.info("ddddddddddd");
        return "/profile";
    }
//
//    @GetMapping("/main")
//    public String main() {
//        return "main";
//    }
//
    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal Student student, ProfileDto requestDto, Model model) {
        Boolean result = memberController.changeProfile(requestDto, student).getBody();
        return "main";
    }
}
