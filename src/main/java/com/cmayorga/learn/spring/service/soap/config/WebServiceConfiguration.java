package com.cmayorga.learn.spring.service.soap.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfiguration {

    /*
     * Configuration of Message Dispatcher Servlet
     * 
     * This is the way to register the Web Service and expose an URL to share this
     * WebService with consumers.
     */

    // This step define the dispatcher servlet to expose the main Url path for the ws
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {

        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();

        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean<MessageDispatcherServlet>(messageDispatcherServlet, "/ws/*");

    }

    // This step define the wsdl definition that is necesary to expose the WSDL
    @Bean(name = "coursedetails")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesSchema) {

        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("courseDetailsPort");
        definition.setTargetNamespace("http://soap.service.spring.learn.cmayorga.com/coursedetails");
        definition.setLocationUri("/ws");
        definition.setSchema(coursesSchema);

        return definition;

    }

    // This step defines the wsdl from the xsd that we have made
    @Bean
    public XsdSchema coursesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("coursedetails.xsd"));
    }
    
    
    
    

}
