package com.project.allclear_course.service.lecture;

import com.project.allclear_course.domain.dto.RegistrationListResponseDto;
import com.project.allclear_course.domain.dto.RegistrationRequestDto;
import com.project.allclear_course.domain.dto.RegistrationResponseDto;
import com.project.allclear_course.domain.entity.Lecture;
import com.project.allclear_course.domain.entity.Registration;
import com.project.allclear_course.repository.lecture.LectureRepository;
import com.project.allclear_course.repository.lecture.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final LectureRepository lectureRepository;

    //수강 신청
    public void register(Long studentId, Long lectureId) {
        final Lecture lecture = lectureRepository.findById(lectureId).get();
        final Registration newRegistration = Registration.builder()
                .studentId(studentId)
                .lecture(lecture)
                .build();
        registrationRepository.save(newRegistration);
    }

    //빠른 수강 신청
    public void quickRegister(Long studentId, RegistrationRequestDto requestDto) {
        final Lecture lecture = lectureRepository.findByCourseNumberAndClassNumber(requestDto.getCourseNumber(), requestDto.getClassNumber()).get();
        final Registration newRegistration = Registration.builder()
                .studentId(studentId)
                .lecture(lecture)
                .build();
        registrationRepository.save(newRegistration);
    }

    //수강 취소
    public void delete(Long registrationId) {
        final Registration registration = registrationRepository.findById(registrationId).get();
        registration.delete();
    }

    //수강신청 내역 조회
    public RegistrationListResponseDto read(Long studentId) {
        final List<Registration> list = registrationRepository.findByStudentId(studentId);
        return RegistrationListResponseDto.from(list);
    }
}
