package com.example.school.entities;

public enum Grade {

    A("A"), B("B"), C("C"), D("D"), F("F");

    private String name;

    Grade(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
