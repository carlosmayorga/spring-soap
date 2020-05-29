package com.cmayorga.learn.spring.service.soap.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.cmayorga.learn.spring.service.soap.coursedetails.DeleteCourseDetailsRequest;
import com.cmayorga.learn.spring.service.soap.coursedetails.DeleteCourseDetailsResponse;
import com.cmayorga.learn.spring.service.soap.coursedetails.GetAllCourseDetailsRequest;
import com.cmayorga.learn.spring.service.soap.coursedetails.GetAllCourseDetailsResponse;
import com.cmayorga.learn.spring.service.soap.coursedetails.GetCourseDetailsRequest;
import com.cmayorga.learn.spring.service.soap.coursedetails.GetCourseDetailsResponse;
import com.cmayorga.learn.spring.service.soap.service.CourseDetailsService;
import com.cmayorga.learn.spring.service.soap.service.CourseDetailsService.Status;
import com.cmayorga.learn.spring.service.soap.mapper.CourseDetailsServiceMapper;
import com.cmayorga.learn.spring.service.soap.model.Course;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    CourseDetailsService service;
    
    @Autowired
    CourseDetailsServiceMapper mapper;

    
    @PayloadRoot(namespace = "http://soap.service.spring.learn.cmayorga.com/coursedetails", 
            localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {

        Course course = service.findById(request.getId());

        return mapper.mapResponseCourseDetails(course);

    }
    

    @PayloadRoot(namespace = "http://soap.service.spring.learn.cmayorga.com/coursedetails", 
            localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {

        List<Course> course = service.findAll();

        return mapper.mapResponseListOfCourseDetails(course);

    }
    
    
    @PayloadRoot(namespace = "http://soap.service.spring.learn.cmayorga.com/coursedetails", 
            localPart = "DeleteCourseDetailsRequest")
    @ResponsePayload
    public DeleteCourseDetailsResponse processDeleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {

        DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
        
        Status status = service.deleteById(request.getId());

        response.setStatus(mapper.mapStatus(status));

        return response;

    }
    

}
