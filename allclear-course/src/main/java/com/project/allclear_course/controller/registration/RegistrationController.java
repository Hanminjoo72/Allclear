package com.project.allclear_course.controller.registration;

import com.project.allclear_course.domain.dto.LectureDto;
import com.project.allclear_course.domain.dto.RegistrationListResponseDto;
import com.project.allclear_course.domain.dto.RegistrationRequestDto;
import com.project.allclear_course.domain.dto.RegistrationResponseDto;
import com.project.allclear_course.domain.entity.Department;
import com.project.allclear_course.service.DepartmentService;
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

    //수강 신청 페이지
    @GetMapping("/register")
    public String registration(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "registration/register";
    }

    //수강 신청
    @PostMapping("/register/{lectureId}")
    public String register(@PathVariable Long lectureId, @ModelAttribute RegistrationRequestDto requestDto) {
        final String url = "http://localhost:8080/api/registration/" + lectureId;
        restTemplate.postForObject(url, requestDto, RegistrationRequestDto.class);
        return "redirect:/registration/register";
    }

    //수강 신청 내역 조회 페이지
    @GetMapping("/detail/{memberId}")
    public String registrationDetail(@PathVariable Long memberId, Model model) {
        final String url = "http://localhost:8080/api/registration/";
        RegistrationListResponseDto response = restTemplate.getForObject(url, RegistrationListResponseDto.class);
        model.addAttribute("response", response.getResponse());
        return "registration/detail";
    }

    //수강 취소
    @GetMapping("/{registrationId}/delete")
    public String delete(@PathVariable("registrationId") Long registrationId) {
        final String url = "http://localhost:8080/api/registration/" + registrationId;
        restTemplate.put(url, registrationId, Long.class);
        return "redirect:/registration/detail";
    }
}
