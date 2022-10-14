package com.example.school.controllers;

import com.example.school.dto.Instructor;
import com.example.school.dto.Message;
import com.example.school.services.InstructorService;
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
@RequestMapping("instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public String list(HttpSession session, Model model) {
        Message message = (Message) session.getAttribute("message");
        if (message != null) {
            model.addAttribute("message", message);
            session.removeAttribute("message");
        }
        List<Instructor> instructors = instructorService.findAll();
        model.addAttribute("instructors", instructors);
        model.addAttribute("title", "Instructors");
        model.addAttribute("current", "Instructors");
        return "instructors/index";
    }

    @GetMapping("create")
    public String showCreate(Model model, Instructor instructor) {
        fillCreateModel(model);
        model.addAttribute("instructor", instructor);
        return "instructors/create_edit";
    }

    @PostMapping("create")
    public String create(@Valid Instructor instructor, BindingResult result, HttpSession session, Model model) {
        if (!result.hasErrors()) {
            if (instructorService.create(instructor)) {
                session.setAttribute("message",
                        new Message(instructor.getFirstMidName() + " added",
                                Message.SUCCESS));
                return "redirect:/instructors";
            } else {
                model.addAttribute("errorMessage", "Instructor Already Exists");
            }
        }
        fillCreateModel(model);
        return "instructors/create_edit";
    }

    @GetMapping("edit/{id}")
    public String showEdit(@PathVariable long id, Model model) {
        fillEditModel(model);
        Instructor instructor = instructorService.findById(id);
        model.addAttribute("instructor", instructor);
        return "instructors/create_edit";
    }

    @PostMapping("edit/{id}")
    public String Edit(
            @PathVariable long id,
            @Valid Instructor instructor,
            BindingResult result, HttpSession session, Model model) {
        if (!result.hasErrors()) {
            instructor.setId(id);
            if (instructorService.update(instructor)) {
                session.setAttribute("message",
                        new Message(instructor.getFirstMidName() + " updated",
                                Message.SUCCESS));
                return "redirect:/instructors";
            } else {
                model.addAttribute("errorMessage", "Error Editing Instructor");
            }
        }
        fillEditModel(model);
        return "instructors/create_edit";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable long id,HttpSession session ){
        Instructor instructor = instructorService.deleteById(id);
        if (instructor != null){
            session.setAttribute("message",
                    new Message(instructor.getFirstMidName() + " deleted",
                            Message.SUCCESS));
        }else{
            session.setAttribute("message",
                    new Message(id + " Instructor not found",
                            Message.ERROR));
        }
        return "redirect:/instructors";
    }

    private void fillCreateModel(Model model) {
        model.addAttribute("title", "Create Instructor");
        model.addAttribute("current", "Instructors");
        model.addAttribute("actionLabel", "Create");
    }
    private void fillEditModel(Model model) {
        model.addAttribute("title", "Edit Instructor");
        model.addAttribute("current", "Instructors");
        model.addAttribute("actionLabel", "Edit");
    }
}

