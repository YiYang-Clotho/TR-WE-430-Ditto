package com.ditto.cookiez;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})

public class CookiezApplication {

    public static void main(String[] args) {
        SpringApplication.run(CookiezApplication.class, args);
    }

}

