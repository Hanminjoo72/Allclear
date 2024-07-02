package com.project.allclear_user.repository;

import com.project.allclear_user.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Student, Long> {
    Student findByLoginId(Long loginId);
}
