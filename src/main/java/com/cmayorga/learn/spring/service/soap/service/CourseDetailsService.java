package com.cmayorga.learn.spring.service.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cmayorga.learn.spring.service.soap.model.Course;

@Service
public class CourseDetailsService {
    
    public enum Status {SUCCESS, FAILURE;}
    
    private static List<Course> courses = new ArrayList<>();

    static {
        Course course1 = new Course(1, "Spring Boot", "10 Steps");
        courses.add(course1);

        Course course2 = new Course(2, "Spring MVC", "20 Steps");
        courses.add(course2);

        Course course3 = new Course(3, "Angular", "20 Steps");
        courses.add(course3);

        Course course4 = new Course(4, "Ionic", "20 Steps");
        courses.add(course4);
    }

    public Course findById(int id) {
        for (Course course : courses) {
            if (course.getId() == id)
                return course;
        }
        return null;
    }

    public List<Course> findAll() {
        return courses;
    }

    public Status deleteById(int id) {

        // Use iterator instead of Foreach

        Iterator<Course> iterator = courses.iterator();

        while (iterator.hasNext()) {
            Course course = iterator.next();

            if (course.getId() == id) {
                iterator.remove();
                return Status.SUCCESS;
            }
        }

        return Status.FAILURE;
    }

}
