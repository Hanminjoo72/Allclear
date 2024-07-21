package com.project.allclear_course.repository.lecture;

import com.project.allclear_course.domain.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}
