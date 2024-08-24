package com.project.allclear_course.controller.registration;

import com.project.allclear_course.domain.dto.LectureDto;
import com.project.allclear_course.domain.dto.RegistrationListResponseDto;
import com.project.allclear_course.domain.dto.RegistrationRequestDto;
import com.project.allclear_course.domain.dto.RegistrationResponseDto;
import com.project.allclear_course.domain.entity.Department;
import com.project.allclear_course.domain.entity.Wishlist;
import com.project.allclear_course.service.DepartmentService;
import com.project.allclear_course.service.WishlistService;
import com.project.allclear_course.service.lecture.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private RestTemplate restTemplate = new RestTemplate();
    private final DepartmentService departmentService;
    private final RegistrationService registrationService;
    private final WishlistService wishlistService;

    //수강 신청 페이지
    @GetMapping("/register")
    public String registration(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        //TODO 사용자 정보 받아오기
        List<Wishlist> wishlist = wishlistService.getWishlistsByStudent(1L);
        List<RegistrationResponseDto> registration = registrationService.read(1L).getResponse();
        model.addAttribute("departments", departments);
        model.addAttribute("wishlist", wishlist);
        model.addAttribute("registration", registration);
        model.addAttribute("request", new RegistrationRequestDto());
        return "registration/register";
    }

    //수강 신청
    @PostMapping("/register/{lectureId}")
    public String register(@PathVariable Long lectureId) {
        //TODO 사용자 정보 받아오기
        registrationService.register(1L, lectureId);
        return "redirect:/registration/register";
    }

    //빠른 수강신청
    @PostMapping("register/quick")
    public String quickRegister(RegistrationRequestDto request) {
        //TODO 사용자 정보 받아오기
        registrationService.quickRegister(1L, request);
        return "redirect:/registration/register";
    }

    //수강 신청 내역 조회 페이지
    @GetMapping("/detail")
    public String registrationDetail(Model model) {
        //TODO 사용자 정보 받아오기
        RegistrationListResponseDto response = registrationService.read(1L);
        model.addAttribute("response", response.getResponse());
        return "registration/detail";
    }

    //수강 취소
    @GetMapping("/{registrationId}/delete")
    public String delete(@PathVariable("registrationId") Long registrationId) {
        registrationService.delete(registrationId);
        return "redirect:/registration/register";
    }
}
