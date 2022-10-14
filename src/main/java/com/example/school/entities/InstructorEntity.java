package com.example.school.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(
        name="instructor",
        uniqueConstraints ={
                @UniqueConstraint(
                        name="instructor_first_name_unigue",
                        columnNames = {"first_name"}
                ),
                @UniqueConstraint(
                        name="instructor_last_name_unigue",
                        columnNames = {"last_name"}
                )
        }
)
public class InstructorEntity {

    @Id
    @SequenceGenerator(
            name = "instructor_sequence",
            sequenceName = "instructor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(generator = "instructor_sequence")
    private  Long id;


    @Column(length = 50,nullable = false ,name="first_name" )
    private String firstMidName;

    @Column(length = 50,nullable = false,name="last_name")
    private String lastName;

    @DateTimeFormat(pattern ="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date hireDate;

    @OneToOne(mappedBy = "instructor")
    @PrimaryKeyJoinColumn
    private OfficeAssignmentEntity officeAssignment;


    @ManyToMany
    @JoinTable(
            name="course_assignment",
            joinColumns = @JoinColumn(name="instructor_id",foreignKey = @ForeignKey(name="course_assignment_instructor_id_fk")),//the current table
            inverseJoinColumns = @JoinColumn(name="course_id",foreignKey = @ForeignKey(name="course_assignment_course_id_fk")),
            uniqueConstraints = @UniqueConstraint(name = "course_assignment_instructor_id_course_id_unique",columnNames = {"instructor_id","course_id"})

    )// the other table
    private Collection<CourseEnitity> courses;

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

    public OfficeAssignmentEntity getOfficeAssignment() {
        return officeAssignment;
    }

    public void setOfficeAssignment(OfficeAssignmentEntity officeAssignment) {
        this.officeAssignment = officeAssignment;
    }

    public Collection<CourseEnitity> getCourses() {
        return courses;
    }

    public void setCourses(Collection<CourseEnitity> courses) {
        this.courses = courses;
    }
}
