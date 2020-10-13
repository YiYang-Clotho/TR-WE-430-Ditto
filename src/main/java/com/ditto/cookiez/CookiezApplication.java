package com.ditto.cookiez;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ditto.cookiez.mapper")
public class CookiezApplication {
    public static void main(String[] args) {
        SpringApplication.run(CookiezApplication.class, args);
    }


}
