package com.ling.vblog.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MyAspect {

    @Before("within(com.ling.vblog.service.*ServiceImpl)")
    public void before(JoinPoint joinPoint){

    }
}
