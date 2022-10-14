package com.example.school.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(
        name="student",
        uniqueConstraints ={
                @UniqueConstraint(
                        name="student_first_name_unigue",
                        columnNames = {"first_name"}
                ),
                @UniqueConstraint(
                        name="student_last_name_unigue",
                        columnNames = {"last_name"}
                )
        }
)
public class StudentEnitity {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(generator = "student_sequence")
    private  Long id;

    @Column(length = 50, name="first_name",nullable = false)
    private String firstMidName;

    @Column(length = 50, name="last_name",nullable = false)
    private String lastName;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date enrollmentDate;

    @OneToMany
    private Collection<EnrollmentEntity> enrollments;

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

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Collection<EnrollmentEntity> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Collection<EnrollmentEntity> enrollments) {
        this.enrollments = enrollments;
    }

    @Transient
    public String getFullName(){
        return lastName+ " " +firstMidName;
    }
}
