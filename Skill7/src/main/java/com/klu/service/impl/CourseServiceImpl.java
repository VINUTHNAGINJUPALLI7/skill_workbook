package com.klu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.Course;
import com.klu.repo.CourseRepo;
import com.klu.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepo repo;

    @Override
    public Course addCourse(Course course) {
        return repo.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return repo.findAll();
    }

    @Override
    public Course getCourseById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Course updateCourse(int id, Course course) {
        if (repo.existsById(id)) {
            course.setCourseId(id);
            return repo.save(course);
        }
        return null;
    }

    @Override
    public String deleteCourse(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Course deleted successfully";
        }
        return "Course not found";
    }

    @Override
    public List<Course> searchByTitle(String title) {
        return repo.findByTitleIgnoreCase(title);
    }
}
