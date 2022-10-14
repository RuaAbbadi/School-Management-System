package com.example.school.dto;

import com.example.school.entities.InstructorEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class FormDepartment implements Serializable {

    private Long departmentId;

    @NotNull
    @Size(min = 3,max = 15)
    private String name;

    @NotNull
    private double budget;

    @NotNull(message = "Please choose a Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @NotNull(message = "Please choose a Instructor")
    public Long administratorId;

    public FormDepartment() {
    }

    public FormDepartment(Long departmentId, String name, double budget, Date startDate, Long administratorId) {
        this.departmentId = departmentId;
        this.name = name;
        this.budget = budget;
        this.startDate = startDate;
        this.administratorId = administratorId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Long getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Long administratorId) {
        this.administratorId = administratorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormDepartment that = (FormDepartment) o;
        return Double.compare(that.budget, budget) == 0 && Objects.equals(departmentId, that.departmentId) && Objects.equals(name, that.name) && Objects.equals(administratorId, that.administratorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, name, budget, administratorId);
    }

    @Override
    public String toString() {
        return "FormDepartment{" +
                "departmentId=" + departmentId +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", administratorId=" + administratorId +
                '}';
    }
}
