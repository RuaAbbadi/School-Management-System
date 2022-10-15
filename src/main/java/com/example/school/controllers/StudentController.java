package com.example.school.controllers;


import com.example.school.dto.Instructor;
import com.example.school.dto.Message;
import com.example.school.dto.PageDto;
import com.example.school.dto.Student;
import com.example.school.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("students")
public class StudentController {

    private  final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String list(
            @RequestParam(value = "p", required = false) Integer currentPage,
            @RequestParam(value = "d", required = false) String direction,
            @RequestParam(value = "o", required = false) String orderBy,
            HttpSession session, Model model
    ) {
        boolean ascending = !"desc".equalsIgnoreCase(direction);
        if (orderBy == null) {
            orderBy = "firstMidName";
        }
        if (currentPage == null) {
            currentPage = 0;
        }
        Message message = (Message) session.getAttribute("message");
        if (message != null) {
            model.addAttribute("message", message);
            session.removeAttribute("message");
        }
        PageDto<Student> students = studentService.list(currentPage, ascending, orderBy);
        model.addAttribute("students", students);
        model.addAttribute("title", "Students");
        model.addAttribute("current", "Students");
        return "students/index";
    }



    @GetMapping("create")
    public String showCreate(Model model, Student student) {
        fillCreateModel(model);
        model.addAttribute("students", student);
        return "students/create_edit";
    }

    @PostMapping("create")
    public String create(@Valid Student student, BindingResult result, HttpSession session, Model model) {
        if (!result.hasErrors()) {
            if (studentService.create(student)) {
                session.setAttribute("message",
                        new Message(student.getFirstMidName() + " added",
                                Message.SUCCESS));
                return "redirect:/students";
            } else {
                model.addAttribute("errorMessage", "Student Already Exists");
            }
        }
        fillCreateModel(model);
        return "students/create_edit";
    }


    @GetMapping("edit/{id}")
    public String showEdit(@PathVariable long id, Model model) {
        fillEditModel(model);
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "students/create_edit";
    }

    @PostMapping("edit/{id}")
    public String Edit(
            @PathVariable long id,
            @Valid Student student,
            BindingResult result, HttpSession session, Model model) {
        if (!result.hasErrors()) {
            student.setId(id);
            if (studentService.update(student)) {
                session.setAttribute("message",
                        new Message(student.getFirstMidName() + " updated",
                                Message.SUCCESS));
                return "redirect:/students";
            } else {
                model.addAttribute("errorMessage", "Error Editing Student");
            }
        }
        fillEditModel(model);
        return "students/create_edit";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable long id,HttpSession session ){
        Student student = studentService.deleteById(id);
        if (student != null){
            session.setAttribute("message",
                    new Message(student.getFirstMidName() + " deleted",
                            Message.SUCCESS));
        }else{
            session.setAttribute("message",
                    new Message(id + " Student not found",
                            Message.ERROR));
        }
        return "redirect:/students";
    }

    private void fillCreateModel(Model model) {
        model.addAttribute("title", "Create Student");
        model.addAttribute("current", "Students");
        model.addAttribute("actionLabel", "Create");
    }
    private void fillEditModel(Model model) {
        model.addAttribute("title", "Edit Stuedent");
        model.addAttribute("current", "Students");
        model.addAttribute("actionLabel", "Edit");
    }

}
