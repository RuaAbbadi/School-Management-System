package com.example.school.entities;


import javax.persistence.*;

@Entity
@Table(name="enrollment")
public class EnrollmentEntity {

    @EmbeddedId // 2 keys as one key
    private StudentCourseKey id ;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name="student_id")
    private StudentEnitity studentId;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name="course_id")
    private CourseEnitity courseId;

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private Grade grade;

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
