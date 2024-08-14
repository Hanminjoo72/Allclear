package com.project.allclear_course.controller.ui;

import com.project.allclear_course.domain.dto.LectureDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class LectureUiController {

    private final RestTemplate restTemplate;

    public LectureUiController() {
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/lectures/new")
    public String showLectureForm(Model model) {
        model.addAttribute("lectureDto", new LectureDto());
        return "lectures/lectureForm";
    }

    @PostMapping("/ui/createLecture")
    public String createLecture(@ModelAttribute LectureDto lectureDto) {
        String apiUrl = "http://localhost:8080/api/lectures";
        restTemplate.postForObject(apiUrl, lectureDto, LectureDto.class);
        return "redirect:/lectures/new";
    }
}
