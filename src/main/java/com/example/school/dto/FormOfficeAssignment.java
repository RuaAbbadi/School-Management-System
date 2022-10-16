package com.example.school.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

public class FormOfficeAssignment implements Serializable {

    private Long id;

    @NotNull
    @Size(min=3,max = 15)
    private String location;


    @NotNull(message = "Please choose Instructor")
    private Long instId;

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public FormOfficeAssignment() {
    }

    public FormOfficeAssignment(Long id, String location, Long instId) {
        this.id = id;
        this.location = location;
        this.instId = instId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormOfficeAssignment that = (FormOfficeAssignment) o;
        return Objects.equals(id, that.id) && Objects.equals(location, that.location) && Objects.equals(instId, that.instId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, instId);
    }

    @Override
    public String toString() {
        return "FormOfficeAssignment{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", instId=" + instId +
                '}';
    }
}
