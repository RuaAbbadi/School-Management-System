package com.example.school.dto;


import java.io.Serializable;
import java.util.Objects;

public class Message implements Serializable {

    public static final int ERROR = 0;
    public static final int SUCCESS = 1;

    private String message;
    private int type;


    public Message(String message, int type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return type == message1.type && Objects.equals(message, message1.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, type);
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", type=" + type +
                '}';
    }
}


