package com.ditto.cookiez;

import com.alibaba.fastjson.JSON;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class WebLogAspect {

    private final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
//
    @Pointcut("execution(public * com.ditto.cookiez.controller..*.*(..))")
    public void controllerLog(){}



    @Before("controllerLog()")
    public void logBeforeController(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();

        // record request
        logger.info("################  URL : " + request.getRequestURL().toString());
        logger.info("################  HTTP_METHOD : " + request.getMethod());

        logger.info("################   THE ARGS OF THE CONTROLLER : " + Arrays.toString(joinPoint.getArgs()));


        logger.info("################    CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());


    }
    @AfterReturning(value = "controllerLog()",returning = "ret")
    public void doAfterReturning(Object ret) {
        logger.info("Response : " + JSON.toJSONString(ret));
        logger.info("----------------------------------------------");
    }

}


