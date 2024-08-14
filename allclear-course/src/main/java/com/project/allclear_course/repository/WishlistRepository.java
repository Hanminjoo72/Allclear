package com.project.allclear_course.repository;

import com.project.allclear_course.domain.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByStudentId(Long studentId);
}
