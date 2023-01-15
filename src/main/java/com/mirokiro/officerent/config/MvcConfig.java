package com.mirokiro.officerent.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/personal").setViewName("personal");
        registry.addViewController("/login").setViewName("login2");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/accessDenied").setViewName("accessDenied");
    }

}
