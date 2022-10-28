package com.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(basePackages = "com.test")
public class ApplicationConfig extends WebMvcConfigurationSupport {

    // this will hold all the configuration for the application
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // This method is used to add resource handlers for serving static resources like images, js, css files etc...
        registry.addResourceHandler("css/**", "images/**")
                .addResourceLocations("classpath:/css/", "classpath:/images/");
    }

    // define a bean for ViewResolver so that the jsp pages can be resolved
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }
}
