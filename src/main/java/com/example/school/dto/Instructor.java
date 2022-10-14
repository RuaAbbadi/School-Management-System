package com.example.school.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class Instructor implements Serializable {

    private  Long id;

    @NotNull
    @Size(max = 15,min = 3)
    private String firstMidName;

    @NotNull
    @Size(max = 15,min = 3)
    private String lastName;

    @NotNull(message = "Please choose a Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;

    public Instructor() {
    }

    public Instructor(Long id, String firstMidName, String lastName, Date hireDate) {
        this.id = id;
        this.firstMidName = firstMidName;
        this.lastName = lastName;
        this.hireDate = hireDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstMidName() {
        return firstMidName;
    }

    public void setFirstMidName(String firstMidName) {
        this.firstMidName = firstMidName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return Objects.equals(id, that.id) && Objects.equals(firstMidName, that.firstMidName) && Objects.equals(lastName, that.lastName) && Objects.equals(hireDate, that.hireDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstMidName, lastName, hireDate);
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstMidName='" + firstMidName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hireDate=" + hireDate +
                '}';
    }
}
