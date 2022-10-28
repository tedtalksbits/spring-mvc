package com.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

// add the Java configuration to bootstrap DispatcherServlet class

public class ApplicationInitializer  implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        // bootstrap the dispatcherservlet, generally the configuration is fetched into a context object
        // but since this is a web application, and we are using annotations, we can use the AnnotationConfigApplicationContext
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // tie up the spring application class with the annotaionconfigapplicationcontext
        context.register(ApplicationConfig.class);

        ServletRegistration.Dynamic servletRegistration =
                servletContext.addServlet("mvc", new DispatcherServlet((WebApplicationContext) context));

        // the default value for setLoadOnStartup is -1,
        // which means that the container initialize this as lazy loading procedure
        servletRegistration.setLoadOnStartup(1);

        // add the mapping, this is the url that the dispatcher servlet will handle
        servletRegistration.addMapping("/");
    }
}
