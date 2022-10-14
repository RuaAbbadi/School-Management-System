package com.example.school.entities;


import javax.persistence.*;

@Entity
@Table(name="office_assignment")
public class OfficeAssignmentEntity {
    @Id
    @Column(name="instructor_id")
    private Long instructorId;

    private String location;



    @OneToOne
    @MapsId
    @JoinColumn(name="instructor_id" ,foreignKey = @ForeignKey(name = "instructor_office_assignment_instructor_id_fk"))
    private InstructorEntity instructor;

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
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
