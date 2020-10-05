package com.ditto.cookiez;

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
        //和页面有关的静态目录都放在项目的static目录下
        //其中OTA表示访问的前缀。"file:D:/OTA/"是文件真实的存储路径
        registry.addResourceHandler("/static/**").addResourceLocations("file:"+ ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/");
    }
}
