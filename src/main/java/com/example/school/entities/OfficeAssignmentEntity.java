package com.example.school.entities;


import javax.persistence.*;

@Entity
@Table(name="office_assignment")
public class OfficeAssignmentEntity {
    @Id
    @Column(name="instructor_id")
    private Long id;

    @Column(unique = true)
    private String location;


    @OneToOne
    @MapsId
    @JoinColumn(name="instructor_id" ,foreignKey = @ForeignKey(name = "instructor_office_assignment_instructor_id_fk"))
    private InstructorEntity instructor;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public InstructorEntity getInstructor() {
        return instructor;
    }

    public void setInstructor(InstructorEntity instructor) {
        this.instructor = instructor;
    }
}
