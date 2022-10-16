package com.example.school.dto;

import com.example.school.entities.Grade;
import com.example.school.entities.StudentCourseKey;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class FormEnrollment implements Serializable {

    private Long id;

    @NotNull(message = "please choose a Student ")
    private Long studentId;

    @NotNull(message = "please choose a Course ")
    private Long courseId;

    @NotNull(message = "please choose a Grade ")
    private Grade grade;


    public FormEnrollment() {
    }

    public FormEnrollment(Long id, Long studentId, Long courseId, Grade grade) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormEnrollment that = (FormEnrollment) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId) && grade == that.grade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId, grade);
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                ", grade=" + grade +
                '}';
    }
}
