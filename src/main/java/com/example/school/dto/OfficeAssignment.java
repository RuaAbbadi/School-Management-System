package com.example.school.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;


public class OfficeAssignment implements Serializable {

    @NotNull
    @Size(min=3,max = 5)
    private String location;

    public OfficeAssignment() {
    }

    public OfficeAssignment(String location) {
        this.location = location;
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
        OfficeAssignment that = (OfficeAssignment) o;
        return Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }

    @Override
    public String toString() {
        return "OfficeAssignment{" +
                "location='" + location + '\'' +
                '}';
    }
}
