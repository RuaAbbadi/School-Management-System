package com.example.school.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Student implements Serializable {
    private  Long id;

    @NotNull(message = "Please choose a Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date enrollmentDate;

    @NotNull
    @Size(max = 15,min =3 )
    private String firstMidName;

    @NotNull
    @Size(max = 15,min =3 )
    private String lastName;


    public Student() {
    }

    public Student(Long id, Date enrollmentDate, String firstMidName, String lastName) {
        this.id = id;
        this.enrollmentDate = enrollmentDate;
        this.firstMidName = firstMidName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(enrollmentDate, student.enrollmentDate) && Objects.equals(firstMidName, student.firstMidName) && Objects.equals(lastName, student.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, enrollmentDate, firstMidName, lastName);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", enrollmentDate=" + enrollmentDate +
                ", firstMidName='" + firstMidName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
