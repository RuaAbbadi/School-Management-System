package com.example.school.controllers;


import com.example.school.dto.*;
import com.example.school.services.CourseService;
import com.example.school.services.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("courses")
public class CourseController {


    private final CourseService courseService;

    private final DepartmentService departmentService;



    public CourseController(CourseService courseService, DepartmentService departmentService) {
        this.courseService = courseService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String list(HttpSession session, Model model) {
        Message message = (Message) session.getAttribute("message");
        if (message != null) {
            model.addAttribute("message", message);
            session.removeAttribute("message");
        }
        List<Course> courses = courseService.findAll();
        model.addAttribute("courses", courses);
        model.addAttribute("title", "Courses");
        model.addAttribute("current", "Courses");
        return "courses/index";
    }

    @GetMapping("create")
    public String showCreate(Model model, FormCourse formCourse) {
        fillCreateModel(model);
        model.addAttribute("formCourse", formCourse);
        return "courses/create_edit";
    }

    @PostMapping("create")
    public String create(@Valid FormCourse formCourse, BindingResult result, HttpSession session, Model model) {
        if (!result.hasErrors()) {
            if (courseService.create(formCourse)) {
                session.setAttribute("message",
                        new Message(formCourse.getTitle() + " added",
                                Message.SUCCESS));
                return "redirect:/courses";
            } else {
                model.addAttribute("errorMessage", "Course Already Exists");
            }
        }
        fillCreateModel(model);
        return "courses/create_edit";
    }

    @GetMapping("edit/{id}")
    public String showEdit(@PathVariable long id, Model model) {
        fillEditModel(model);
        Course course = courseService.findById(id);
        FormCourse formCourse = new FormCourse(
                course.getId(), course.getTitle(),course.getCredits(),course.getDepartment() != null ? course.getDepartment().getDepartmentId() : null
        );
        model.addAttribute("formCourse", formCourse);
        return "courses/create_edit";
    }

    @PostMapping("edit/{id}")
    public String Edit(
            @PathVariable long id,
            @Valid FormCourse formCourse,
            BindingResult result, HttpSession session, Model model) {
        if (!result.hasErrors()) {
            formCourse.setDepartmentId(id);
            if (courseService.update(formCourse)) {
                session.setAttribute("message",
                        new Message(formCourse.getTitle() + " updated",
                                Message.SUCCESS));
                return "redirect:/courses";
            } else {
                model.addAttribute("errorMessage", "Error Editing Course");
            }
        }
        fillEditModel(model);
        return "courses/create_edit";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable long id,HttpSession session ){
        Course course = courseService.deleteById(id);
        if (course != null){
            session.setAttribute("message",
                    new Message(course.getTitle() + " deleted",
                            Message.SUCCESS));
        }else{
            session.setAttribute("message",
                    new Message(id + " Course not found",
                            Message.ERROR));
        }
        return "redirect:/courses";
    }

    private void fillEditModel(Model model) {
        model.addAttribute("title", "Edit Course");
        model.addAttribute("current", "Courses");
        model.addAttribute("actionLabel", "Edit");
        model.addAttribute("departments", departmentService.findAll());
    }


    private void fillCreateModel(Model model) {
        model.addAttribute("title", "Create Course");
        model.addAttribute("current", "Courses");
        model.addAttribute("actionLabel", "Create");
        model.addAttribute("departments", departmentService.findAll());
    }


}
