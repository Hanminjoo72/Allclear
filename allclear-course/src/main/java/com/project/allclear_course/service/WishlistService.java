package com.project.allclear_course.service;

import com.project.allclear_course.domain.entity.Lecture;
import com.project.allclear_course.domain.entity.Wishlist;
import com.project.allclear_course.repository.LectureRepository;
import com.project.allclear_course.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private LectureRepository lectureRepository;

    public List<Wishlist> getWishlistsByStudent(Long studentId) {
        return wishlistRepository.findByStudentId(studentId);
    }

    public Wishlist addWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    public void deleteWishlist(Long wishlistId) {
        wishlistRepository.deleteById(wishlistId);
    }

    public void updateWishlistPriority(Long wishlistId, int priority) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).orElseThrow(() -> new IllegalArgumentException("Invalid wishlist Id:" + wishlistId));
        wishlist.setPriority(priority);
        wishlistRepository.save(wishlist);
    }
    public void addToWishlist(Long lectureId, Long studentId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid lecture Id:" + lectureId));
        Wishlist wishlist = new Wishlist();
        wishlist.setLecture(lecture);
        wishlist.setStudentId(studentId); // studentId를 직접 설정
        wishlist.setPriority(0);
        wishlistRepository.save(wishlist);
    }
}
