package com.example.school.dto;

import com.example.school.entities.CourseEnitity;
import com.example.school.entities.Grade;
import com.example.school.entities.StudentCourseKey;
import com.example.school.entities.StudentEnitity;

import javax.persistence.*;
import java.io.Serializable;

public class Enrollment implements Serializable {

    private StudentCourseKey id ;

    private StudentEnitity studentId;

    private CourseEnitity courseId;

    private Grade grade;

    public Enrollment() {
    }

    public Enrollment(StudentCourseKey id, StudentEnitity studentId, CourseEnitity courseId, Grade grade) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public StudentCourseKey getId() {
        return id;
    }

    public void setId(StudentCourseKey id) {
        this.id = id;
    }

    public StudentEnitity getStudentId() {
        return studentId;
    }

    public void setStudentId(StudentEnitity studentId) {
        this.studentId = studentId;
    }

    public CourseEnitity getCourseId() {
        return courseId;
    }

    public void setCourseId(CourseEnitity courseId) {
        this.courseId = courseId;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
