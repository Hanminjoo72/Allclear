package com.project.allclear_course.controller;

import com.project.allclear_course.domain.entity.Lecture;
import com.project.allclear_course.service.LectureService;
import com.project.allclear_course.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lectures")
public class LecturesController {

    private final LectureService lectureService;
    private final WishlistService wishlistService;

    @Autowired
    public LecturesController(LectureService lectureService, WishlistService wishlistService) {
        this.lectureService = lectureService;
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public String showLectures(Model model, @RequestParam(required = false) Long studentId) {
        List<Lecture> lectures = lectureService.getAllLectures();
        model.addAttribute("lectures", lectures);
        model.addAttribute("studentId", studentId);
        return "lectures";
    }

    @GetMapping("/add-to-wishlist/{lectureId}")
    public String addToWishlist(@PathVariable Long lectureId, @RequestParam Long studentId) {
        wishlistService.addToWishlist(lectureId, studentId);
        return "redirect:/wishlists?studentId=" + studentId;
    }
}
