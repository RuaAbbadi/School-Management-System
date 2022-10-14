package com.example.school.controllers;


import com.example.school.dto.Department;
import com.example.school.dto.FormDepartment;
import com.example.school.dto.Message;
import com.example.school.services.DepartmentService;
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
@RequestMapping("departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final InstructorService instructorService;

    public DepartmentController(DepartmentService departmentService, InstructorService instructorService) {
        this.departmentService = departmentService;
        this.instructorService = instructorService;
    }

    @GetMapping
    public String list(HttpSession session, Model model) {
        Message message = (Message) session.getAttribute("message");
        if (message != null) {
            model.addAttribute("message", message);
            session.removeAttribute("message");
        }
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        model.addAttribute("title", "Departments");
        model.addAttribute("current", "Departments");
        return "departments/index";
    }

    @GetMapping("create")
    public String showCreate(Model model, FormDepartment formDepartment) {
        fillCreateModel(model);
        model.addAttribute("formDepartment", formDepartment);
        return "departments/create_edit";
    }

    @PostMapping("create")
    public String create(@Valid FormDepartment formDepartment, BindingResult result, HttpSession session, Model model) {
        if (!result.hasErrors()) {
            if (departmentService.create(formDepartment)) {
                session.setAttribute("message",
                        new Message(formDepartment.getName() + " added",
                                Message.SUCCESS));
                return "redirect:/departments";
            } else {
                model.addAttribute("errorMessage", "Department Already Exists");
            }
        }
        fillCreateModel(model);
        return "departments/create_edit";
    }

    @GetMapping("edit/{id}")
    public String showEdit(@PathVariable long id, Model model) {
        fillEditModel(model);
        Department department = departmentService.findById(id);
        FormDepartment formDepartment = new FormDepartment(
                department.getDepartmentId(), department.getName(),department.getBudget(),department.getStartDate(),department.getAdministrator() != null ? department.getAdministrator().getId() : null
        );
        model.addAttribute("formDepartment", formDepartment);
        return "departments/create_edit";
    }

    @PostMapping("edit/{id}")
    public String Edit(
            @PathVariable long id,
            @Valid FormDepartment formDepartment,
            BindingResult result, HttpSession session, Model model) {
        if (!result.hasErrors()) {
            formDepartment.setDepartmentId(id);
            if (departmentService.update(formDepartment)) {
                session.setAttribute("message",
                        new Message(formDepartment.getName() + " updated",
                                Message.SUCCESS));
                return "redirect:/departments";
            } else {
                model.addAttribute("errorMessage", "Error Editing Department");
            }
        }
        fillEditModel(model);
        return "departments/create_edit";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable long id,HttpSession session ){
        Department department = departmentService.deleteById(id);
        if (department != null){
            session.setAttribute("message",
                    new Message(department.getName() + " deleted",
                            Message.SUCCESS));
        }else{
            session.setAttribute("message",
                    new Message(id + " Department not found",
                            Message.ERROR));
        }
        return "redirect:/departments";
    }
    private void fillEditModel(Model model) {
        model.addAttribute("title", "Edit Department");
        model.addAttribute("current", "Departments");
        model.addAttribute("actionLabel", "Edit");
        model.addAttribute("instructors", instructorService.findAll());
    }


    private void fillCreateModel(Model model) {
        model.addAttribute("title", "Create Department");
        model.addAttribute("current", "Departments");
        model.addAttribute("actionLabel", "Create");
        model.addAttribute("instructors", instructorService.findAll());
    }



}
