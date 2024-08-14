package com.project.allclear_course.repository;

import com.project.allclear_course.domain.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College, Long> {
}

