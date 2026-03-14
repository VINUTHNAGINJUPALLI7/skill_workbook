package com.klu.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StudentAnnotation {

    @Value("201")
    private int studentId;

    @Value("Ananya")
    private String name;

    private String course;
    private int year;

    // Constructor Injection
    public StudentAnnotation(
            @Value("Python") String course,
            @Value("2025") int year) {
        this.course = course;
        this.year = year;
    }

    // Setter Injection
    public void setCourse(@Value("Spring Boot") String course) {
        this.course = course;
    }

    public void setYear(@Value("2026") int year) {
        this.year = year;
    }

    public void display() {
        System.out.println("Student ID : " + studentId);
        System.out.println("Name       : " + name);
        System.out.println("Course     : " + course);
        System.out.println("Year       : " + year);
    }
}

