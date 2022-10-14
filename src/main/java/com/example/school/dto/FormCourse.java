package com.example.school.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

public class FormCourse implements Serializable {

    private Long id;
    @NotNull
    @Size(min = 3,max = 15)
    private String title;
    @NotNull
    private Integer credits;

    @NotNull(message = "Please choode a department")
    private Long departmentId;

    public FormCourse() {
    }

    public FormCourse(Long id, String title, Integer credits, Long departmentId) {
        this.id = id;
        this.title = title;
        this.credits = credits;
        this.departmentId = departmentId;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormCourse that = (FormCourse) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(credits, that.credits) && Objects.equals(departmentId, that.departmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, credits, departmentId);
    }

    @Override
    public String toString() {
        return "CourseForm{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", credits=" + credits +
                ", departmentId=" + departmentId +
                '}';
    }
}
