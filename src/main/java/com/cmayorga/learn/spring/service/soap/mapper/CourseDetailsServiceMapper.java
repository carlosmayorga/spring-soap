package com.cmayorga.learn.spring.service.soap.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cmayorga.learn.spring.service.soap.coursedetails.CourseDetails;
import com.cmayorga.learn.spring.service.soap.coursedetails.GetAllCourseDetailsResponse;
import com.cmayorga.learn.spring.service.soap.coursedetails.GetCourseDetailsResponse;
import com.cmayorga.learn.spring.service.soap.model.Course;
import com.cmayorga.learn.spring.service.soap.service.CourseDetailsService.Status;


@Component
public class CourseDetailsServiceMapper {
    
    
    public GetCourseDetailsResponse mapResponseCourseDetails(Course course) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        
        response.setCourseDetails(mapEachCourse(course));
        
        return response;
    }
    
    public GetAllCourseDetailsResponse mapResponseListOfCourseDetails(List<Course> courses) {
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
        
        for (Course course : courses) {
            CourseDetails mapCourse = mapEachCourse(course);
            response.getCourseDetails().add(mapCourse);
        }
        
        
        return response;
    }
    

    public CourseDetails mapEachCourse(Course course) {
        CourseDetails courseDetails = new CourseDetails();
        
        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());
        return courseDetails;
    }
    
    
    public com.cmayorga.learn.spring.service.soap.coursedetails.Status mapStatus(Status status) {
        if(status==Status.FAILURE)
            return com.cmayorga.learn.spring.service.soap.coursedetails.Status.FAILURE;
        return com.cmayorga.learn.spring.service.soap.coursedetails.Status.SUCCESS;
    }
    

}
