package com.project.allclear_course.controller.ui;

import com.project.allclear_course.domain.entity.Department;
import com.project.allclear_course.domain.entity.Lecture;
import com.project.allclear_course.form.LectureSearchForm;
import com.project.allclear_course.form.SearchByLectureCodeForm;
import com.project.allclear_course.form.SearchByKeywordForm;
import com.project.allclear_course.service.DepartmentService;
import com.project.allclear_course.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/lectures")
public class SearchLectureUiController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LectureService lectureService;

    @GetMapping("/search")
    public String showLectureSearchForm(Model model,
                                        @RequestParam(value = "searchType", required = false, defaultValue = "") String searchType) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        model.addAttribute("searchType", searchType);  // Add searchType to model

        if ("courseName".equals(searchType)) {
            model.addAttribute("SearchByKeywordForm", new SearchByKeywordForm());
        } else if ("courseCode".equals(searchType)) {
            model.addAttribute("SearchByCourseCodeForm", new SearchByLectureCodeForm());
        } else {
            model.addAttribute("LectureSearchForm", new LectureSearchForm());
        }
        return "lectures/search";
    }


    @GetMapping("/search/department")
    public String searchByDepartment(Model model, @ModelAttribute LectureSearchForm form,
                                     @RequestParam(value = "searchType", required = false, defaultValue = "") String searchType) {
        List<Lecture> lectures = lectureService.searchByDepartment(form.getDepartmentId(), form.getGrade(), form.getCourseName());
        model.addAttribute("lectures", lectures);
        model.addAttribute("searchType", searchType);  // Add searchType to model'
        System.out.println("searchType"+searchType);
        return "lectures/search";
    }
    @GetMapping("/search/keyword")
    public String searchByCourse(Model model, @ModelAttribute SearchByKeywordForm form,
                                 @RequestParam(value = "searchType", required = false, defaultValue = "") String searchType) {
        List<Lecture> lectures = lectureService.searchByKeyword(form.getKeyword(), form.getLectureName());
        model.addAttribute("lectures", lectures);
        model.addAttribute("searchType", searchType);  // Add searchType to model
        System.out.println("searchType"+searchType);
        return "lectures/search";
    }
    @GetMapping("/search/lectureCode")
    public String searchByCourseCode(Model model, @ModelAttribute SearchByLectureCodeForm form, @RequestParam(value = "searchType", required = false, defaultValue = "") String searchType) {
        List<Lecture> lectures = lectureService.searchByLecture(form.getLectureCode(), form.getDivision());
        model.addAttribute("lectures", lectures);
        model.addAttribute("searchType", searchType);  // Add searchType to model
        System.out.println("searchType"+searchType);
        return "lectures/search";
    }
}
