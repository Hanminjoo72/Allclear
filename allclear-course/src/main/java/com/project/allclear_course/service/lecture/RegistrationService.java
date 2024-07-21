package com.project.allclear_course.service.lecture;

import com.project.allclear_course.domain.dto.RegistrationRequestDto;
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

}
