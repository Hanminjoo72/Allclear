package com.project.allclear_course.controller;

import com.project.allclear_course.domain.entity.Lecture;
import com.project.allclear_course.domain.entity.Wishlist;
import com.project.allclear_course.service.LectureService;
import com.project.allclear_course.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WishlistController {

    @RequestMapping("/wishlist")
    public String board() {
        return "wishlist/wishlist";
    }
}
/*@RequestMapping("/wishlists")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private LectureService lectureService;

    @GetMapping
    public String getWishlists(Model model, @RequestParam Long studentId) {
        List<Wishlist> wishlists = wishlistService.getWishlistsByStudent(studentId);
        wishlists.sort((w1, w2) -> Integer.compare(w1.getPriority(), w2.getPriority()));
        model.addAttribute("wishlists", wishlists);
        model.addAttribute("studentId", studentId);
        return "wishlists";
    }

    @PostMapping
    public String addWishlist(@RequestParam Long studentId, @RequestParam List<Long> lectureIds) {
        for (Long lectureId : lectureIds) {
            Lecture lecture = lectureService.getLectureById(lectureId);
            Wishlist wishlist = new Wishlist();
            wishlist.setStudentId(studentId);
            wishlist.setLecture(lecture);
            wishlist.setPriority(0);
            wishlistService.addWishlist(wishlist);
        }
        return "redirect:/wishlists?studentId=" + studentId;
    }

    @PostMapping("/{wishlistId}/delete")
    public String deleteWishlist(@PathVariable Long wishlistId, @RequestParam Long studentId) {
        wishlistService.deleteWishlist(wishlistId);
        return "redirect:/wishlists?studentId=" + studentId;
    }

    @PostMapping("/{wishlistId}/priority")
    public String updateWishlistPriority(@PathVariable Long wishlistId, @RequestParam int priority, @RequestParam Long studentId) {
        wishlistService.updateWishlistPriority(wishlistId, priority);
        return "redirect:/wishlists?studentId=" + studentId;
    }
}*/
