package com.example.school.dto;

import com.example.school.entities.InstructorEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;


public class OfficeAssignment implements Serializable {

    private Long id;
    @NotNull
    @Size(min=3,max = 5)
    private String location;
    @NotNull
    private InstructorEntity instructor;

    public OfficeAssignment() {
    }

    public OfficeAssignment(String location, InstructorEntity instructor) {
        this.location = location;
        this.instructor = instructor;
    }


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeAssignment that = (OfficeAssignment) o;
        return Objects.equals(id, that.id) && Objects.equals(location, that.location) && Objects.equals(instructor, that.instructor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, instructor);
    }

    @Override
    public String toString() {
        return "OfficeAssignment{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", instructor=" + instructor +
                '}';
    }
}
