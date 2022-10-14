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

}
