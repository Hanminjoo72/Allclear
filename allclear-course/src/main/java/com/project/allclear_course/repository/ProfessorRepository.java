package com.project.allclear_course.repository;

import com.project.allclear_course.domain.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
