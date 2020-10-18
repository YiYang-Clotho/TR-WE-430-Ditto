package com.ditto.cookiez;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.ditto.cookiez.mapper")
public class CookiezApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(CookiezApplication.class, args);
    }


}
