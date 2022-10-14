package com.example.school.entities;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="course")
public class CourseEnitity {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(generator = "course_sequence")
    private Long id;

    @Column(length = 50,nullable = false,unique = true)
    private String title;
    private Integer credits;

    @ManyToOne
    @JoinColumn(name="department_id",foreignKey = @ForeignKey(name="course_deperment_id_fk"))
    private DepartmentEntity department;


    @ManyToMany
    private Collection<InstructorEntity> instructors;

    @OneToMany
    private Collection<EnrollmentEntity> enrollments;

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

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public Collection<InstructorEntity> getInstructors() {
        return instructors;
    }

    public void setInstructors(Collection<InstructorEntity> instructors) {
        this.instructors = instructors;
    }

    public Collection<EnrollmentEntity> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Collection<EnrollmentEntity> enrollments) {
        this.enrollments = enrollments;
    }
}
