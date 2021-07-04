package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * @program: SpringBoot_DI
 * @description: 日志处理
 * @author: 钱金林
 * @create: 2021-05-15 19:39
 **/
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(public * com.example.demo.controller..*.*(..))")
    public void controllerLogMethod(){

    };
    /**
     * 方法执行前
     * */
    @Before("controllerLogMethod()")
    public void LogControl(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes == null){
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        log.info("[Java][Main][Method]请求方式"+request.getMethod());
        log.info("[Java][Main][URL]请求URL"+request.getRequestURL().toString());
        log.info("[Java][Main][IP]请求IP地址"+request.getRemoteHost());
        log.info("[Java][Main][Params]请求参数"+ Arrays.toString(joinPoint.getArgs()));
        log.info("[Java][Main][Class]请求方法名" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }











}
