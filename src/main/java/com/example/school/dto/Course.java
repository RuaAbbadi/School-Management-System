package com.example.school.dto;

import com.example.school.entities.DepartmentEntity;
import com.example.school.entities.InstructorEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

public class Course implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 3,max = 15)
    private String title;
    @NotNull
    private Integer credits;

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public Collection<InstructorEntity> getInstructors() {
        return instructors;
    }

    public void setInstructors(Collection<InstructorEntity> instructors) {
        this.instructors = instructors;
    }

    private DepartmentEntity department;

    private Collection<InstructorEntity> instructors;


    public Course() {
    }

    public Course(Long id, String title, Integer credits) {
        this.id = id;
        this.title = title;
        this.credits = credits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }
}
