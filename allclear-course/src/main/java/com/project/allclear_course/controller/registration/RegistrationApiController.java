package com.project.allclear_course.controller.registration;

import com.project.allclear_course.domain.dto.RegistrationRequestDto;
import com.project.allclear_course.domain.entity.Registration;
import com.project.allclear_course.service.lecture.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/registration")
public class RegistrationApiController {

    private final RegistrationService registrationService;

    //수강 신청
    @PostMapping("/{lectureId}")
    public ResponseEntity<Void> register(@PathVariable Long lectureId) {
        //TODO 사용자 정보 받아오기
        registrationService.register(1L, lectureId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
