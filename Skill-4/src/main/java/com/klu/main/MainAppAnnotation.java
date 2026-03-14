package com.klu.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.klu.config.AppConfig;
import com.klu.model.StudentAnnotation;

public class MainAppAnnotation {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        StudentAnnotation student = context.getBean(StudentAnnotation.class);
        student.display();
    }
}
