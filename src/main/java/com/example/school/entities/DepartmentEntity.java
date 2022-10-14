package com.example.school.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name="department")
public class DepartmentEntity {
    @Id
    @SequenceGenerator(
            name = "department_sequence",
            sequenceName = "department_sequence",
            allocationSize = 1
    )
    @GeneratedValue(generator = "department_sequence")
    private Long departmentId;

    @Column(length = 50,nullable = false,unique = true)
    private String name;

    private double budget;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @OneToOne
    @JoinColumn(name = "Instructor_id",foreignKey = @ForeignKey(name="department_administrator_instructor_id_fk"))
    public InstructorEntity administrator;


    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    public Collection<CourseEnitity> courses;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public InstructorEntity getAdministrator() {
        return administrator;
    }

    public void setAdministrator(InstructorEntity administrator) {
        this.administrator = administrator;
    }

    public Collection<CourseEnitity> getCourses() {
        return courses;
    }

    public void setCourses(Collection<CourseEnitity> courses) {
        this.courses = courses;
    }
}
