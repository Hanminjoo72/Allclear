package com.project.allclear_course.controller;

import com.project.allclear_course.domain.entity.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    @RequestMapping("/board")
    public String board() {
        return "board/home";
    }
}
