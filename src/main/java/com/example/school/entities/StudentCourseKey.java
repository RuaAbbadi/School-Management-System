package com.example.school.entities;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class StudentCourseKey implements Serializable {


    private static final long serialVersionUID = 3996240531467993178L;
    @Column(name="student_id")
    private Long studentId;

    @Column(name="course_id")
    private Long courseId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCourseKey that = (StudentCourseKey) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }


}
