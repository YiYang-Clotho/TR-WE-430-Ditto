package com.ditto.cookiez.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Zhihao Liang
 * @date 2020/9/30 13:39
 * @email s3798366@student.rmit.edu.au
 */

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

    }
}
