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

public class Department implements Serializable {

    private Long departmentId;

    @NotNull
    @Size(min = 3,max = 15)
    private String name;

    @NotNull
    private double budget;

    @NotNull
    public InstructorEntity administrator;

    public InstructorEntity getAdministrator() {
        return administrator;
    }

    public void setAdministrator(InstructorEntity administrator) {
        this.administrator = administrator;
    }

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    public Department() {
    }

    public Department(Long departmentId, String name, double budget, Date startDate) {
        this.departmentId = departmentId;
        this.name = name;
        this.budget = budget;
        this.startDate = startDate;
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

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Double.compare(that.budget, budget) == 0 && Objects.equals(departmentId, that.departmentId) && Objects.equals(name, that.name) && Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, name, budget, startDate);
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", startDate=" + startDate +
                '}';
    }
}
