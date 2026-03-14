package com.klu.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {

    private int id;
    private String name;
    private String gender;

    @Autowired
    private Certification certification;

    public Student() {
        this.id = 167;
        this.name = "Meghana";
        this.gender = "Female";
    }

    public void displayStudentDetails() {
        System.out.println("Student ID   : " + id);
        System.out.println("Student Name : " + name);
        System.out.println("Gender       : " + gender);
        System.out.println("Certification: " + certification);
    }
}