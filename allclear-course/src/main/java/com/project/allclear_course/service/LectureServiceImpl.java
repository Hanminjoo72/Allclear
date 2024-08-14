package com.project.allclear_course.service;

import com.project.allclear_course.domain.entity.Lecture;
import com.project.allclear_course.repository.LectureRepository;
import com.project.allclear_course.service.LectureService;
import com.project.allclear_course.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LectureServiceImpl implements LectureService {

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private WishlistService wishlistService;

    @Override
    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

    @Override
    public Lecture getLectureById(Long id) {
        return lectureRepository.findById(id).orElse(null);
    }

    @Override
    public void addToWishlist(Long lectureId, Long studentId) {
        wishlistService.addToWishlist(lectureId, studentId);
    }
}
