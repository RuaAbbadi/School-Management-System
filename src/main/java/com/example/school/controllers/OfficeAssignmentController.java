package com.example.school.controllers;


import com.example.school.dto.*;
import com.example.school.services.InstructorService;
import com.example.school.services.OfficeAssignmentService;
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
@RequestMapping("office")
public class OfficeAssignmentController {

    private  final OfficeAssignmentService officeAssignmentService;

    private final InstructorService instructorService;

    public OfficeAssignmentController(OfficeAssignmentService officeAssignmentService, InstructorService instructorService) {
        this.officeAssignmentService = officeAssignmentService;
        this.instructorService = instructorService;
    }

    @GetMapping
    public String list(HttpSession session, Model model) {
        Message message = (Message) session.getAttribute("message");
        if (message != null) {
            model.addAttribute("message", message);
            session.removeAttribute("message");
        }
        List<OfficeAssignment> officeAssignments = officeAssignmentService.findAll();
        model.addAttribute("officeAssignments", officeAssignments);
        model.addAttribute("title", "officeAssignments");
        model.addAttribute("current", "Offices");
        return "office/index";
    }

    @GetMapping("create")
    public String showCreate(Model model, FormOfficeAssignment formOfficeAssignment) {
        fillCreateModel(model);
        model.addAttribute("formOfficeAssignment", formOfficeAssignment);
        return "office/create_edit";
    }

    @PostMapping("create")
    public String create(@Valid FormOfficeAssignment formOfficeAssignment, BindingResult result, HttpSession session, Model model) {
        if (!result.hasErrors()) {
            if (officeAssignmentService.create(formOfficeAssignment)) {
                session.setAttribute("message",
                        new Message("Office"+ " added",
                                Message.SUCCESS));
                return "redirect:/office";
            } else {
                model.addAttribute("errorMessage", "Office Already Taken");
            }
        }
        fillCreateModel(model);
        return "office/create_edit";
    }

    @GetMapping("edit/{id}")
    public String showEdit(@PathVariable long id, Model model) {
        fillEditModel(model);
        OfficeAssignment officeAssignment = officeAssignmentService.findById(id);
        FormOfficeAssignment formOfficeAssignment = new FormOfficeAssignment(
                officeAssignment.getId(),officeAssignment.getLocation(), officeAssignment.getInstructor() != null ? officeAssignment.getInstructor().getId() : null
        );
        model.addAttribute("formOfficeAssignment", formOfficeAssignment);
        return "office/create_edit";
    }

    @PostMapping("edit/{id}")
    public String Edit(
            @PathVariable long id,
            @Valid FormOfficeAssignment formOfficeAssignment,
            BindingResult result, HttpSession session, Model model) {
        if (!result.hasErrors()) {
            formOfficeAssignment.setId(id);
            if (officeAssignmentService.update(formOfficeAssignment)) {
                session.setAttribute("message",
                        new Message("Office" + " updated",
                                Message.SUCCESS));
                return "redirect:/office";
            } else {
                model.addAttribute("errorMessage", "Error Editing Office");
            }
        }
        fillEditModel(model);
        return "office/create_edit";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable long id,HttpSession session ){
        OfficeAssignment officeAssignment = officeAssignmentService.deleteById(id);
        if (officeAssignment != null){
            session.setAttribute("message",
                    new Message("Office" + " deleted",
                            Message.SUCCESS));
        }else{
            session.setAttribute("message",
                    new Message(id + " Office not found",
                            Message.ERROR));
        }
        return "redirect:/office";
    }


    private void fillEditModel(Model model) {
        model.addAttribute("title", "Edit Office");
        model.addAttribute("current", "Offices");
        model.addAttribute("actionLabel", "Edit");
        model.addAttribute("instructors", instructorService.findAll());
    }


    private void fillCreateModel(Model model) {
        model.addAttribute("title", "Create Office");
        model.addAttribute("current", "Offices");
        model.addAttribute("actionLabel", "Create");
        model.addAttribute("instructors", instructorService.findAll());
    }

}