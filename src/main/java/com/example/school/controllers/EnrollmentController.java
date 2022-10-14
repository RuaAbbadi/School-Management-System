package com.example.school.controllers;


import com.example.school.dto.*;
import com.example.school.entities.Grade;
import com.example.school.services.CourseService;
import com.example.school.services.EnrollmentService;
import com.example.school.services.StudentService;
import org.modelmapper.ModelMapper;
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
@RequestMapping("enrollments")
public class EnrollmentController {

    private  final EnrollmentService enrollmentService;
    private final StudentService studentService;

    private final CourseService courseService;

    private  final ModelMapper mapper;


    public EnrollmentController(EnrollmentService enrollmentService, StudentService studentService, CourseService courseService, ModelMapper mapper) {
        this.enrollmentService = enrollmentService;
        this.studentService = studentService;
        this.courseService = courseService;
        this.mapper = mapper;
    }

    @GetMapping
    public String list(HttpSession session, Model model) {
        Message message = (Message) session.getAttribute("message");
        if (message != null) {
            model.addAttribute("message", message);
            session.removeAttribute("message");
        }
        List<Enrollment> enrollments = enrollmentService.findAll();
        model.addAttribute("enrollments", enrollments);
        model.addAttribute("title", "Enrollments");
        model.addAttribute("current", "Enrollments");
        return "enrollments/index";
    }

    @GetMapping("create")
    public String showCreate(Model model, FormEnrollment formEnrollment) {
        fillCreateModel(model);
        model.addAttribute("formEnrollment", formEnrollment);
        model.addAttribute("grades", Grade.values());


        return "enrollments/create_edit";
    }

    @PostMapping("create")
    public String create(@Valid FormEnrollment formEnrollment, BindingResult result, HttpSession session, Model model) {
        if (!result.hasErrors()) {
            if (enrollmentService.create(formEnrollment)) {
                session.setAttribute("message",
                        new Message("Enrollment" + " added",
                                Message.SUCCESS));
                return "redirect:/enrollments";
            } else {
                model.addAttribute("errorMessage", "Enrollments Already Exists");
            }
        }
        fillCreateModel(model);
        return "enrollments/create_edit";
    }

    @GetMapping("edit/{id}")
    public String showEdit(@PathVariable long id, Model model) {
        fillEditModel(model);
        Enrollment enrollment = enrollmentService.findById(id);
        FormEnrollment formEnrollment = new FormEnrollment(
             enrollment.getStudentId()!= null ? enrollment.getStudentId().getId() : null ,  enrollment.getCourseId()!= null ? enrollment.getCourseId().getId() : null,
                enrollment.getGrade()
        );
        model.addAttribute("formEnrollment", formEnrollment);
        return "enrollments/create_edit";
    }

    @PostMapping("edit/{id}")
    public String Edit(
            @PathVariable long id,
            @Valid FormEnrollment formEnrollment,
            BindingResult result, HttpSession session, Model model) {
        if (!result.hasErrors()) {
            formEnrollment.setStudentId(id);
            if (enrollmentService.update(formEnrollment)) {
                session.setAttribute("message",
                        new Message("Enrollment" + " updated",
                                Message.SUCCESS));
                return "redirect:/enrollments";
            } else {
                model.addAttribute("errorMessage", "Error Editing Enrollment");
            }
        }
        fillEditModel(model);
        return "enrollments/create_edit";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable long id,HttpSession session ){
        Enrollment enrollment = enrollmentService.deleteById(id);
        if (enrollment != null){
            session.setAttribute("message",
                    new Message("Enrollment" + " deleted",
                            Message.SUCCESS));
        }else{
            session.setAttribute("message",
                    new Message(id + " Enrollment not found",
                            Message.ERROR));
        }
        return "redirect:/enrollments";
    }

    private void fillEditModel(Model model) {
        model.addAttribute("title", "Edit Enrollment ");
        model.addAttribute("current", "EnrollmentS");
        model.addAttribute("actionLabel", "Edit");
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("courses", courseService.findAll());
    }


    private void fillCreateModel(Model model) {
        model.addAttribute("title", "Create Enrollment");
        model.addAttribute("current", "EnrollmentS");
        model.addAttribute("actionLabel", "Create");
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("courses", courseService.findAll());
    }






}
