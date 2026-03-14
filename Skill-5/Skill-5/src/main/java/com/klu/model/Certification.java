package com.klu.model;

import org.springframework.stereotype.Component;

@Component
public class Certification {

    private int id;
    private String name;
    private String dateOfCompletion;

    public Certification() {
        this.id = 101;
        this.name = "Java Full Stack";
        this.dateOfCompletion = "19-Jan-2025";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateOfCompletion() {
        return dateOfCompletion;
    }

    @Override
    public String toString() {
        return "Certification [Id=" + id + ", Name=" + name +
               ", DateOfCompletion=" + dateOfCompletion + "]";
    }
}