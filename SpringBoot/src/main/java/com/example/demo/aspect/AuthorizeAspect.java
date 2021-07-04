package com.example.demo.aspect;

import com.example.demo.annotation.Authorize;
import com.example.demo.utils.RedisUtil;
import com.example.demo.utils.ResultUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @program: SpringBoot_DI
 * @description: Authorize注解实现
 * @author: 钱金林
 * @create: 2021-05-16 12:35
 **/
@Aspect
@Component
public class AuthorizeAspect {

    @Autowired
    private RedisUtil redisUtil;


    @Pointcut("@annotation(com.example.demo.annotation.Authorize))")
    private void pointCut() {
    }

    ;

    @Around("pointCut()")
    public Object validate(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        Authorize authorize = method.getAnnotation(Authorize.class);
        String accessToken = (String) redisUtil.get("token");
        String token = "";
        Object[] params = joinPoint.getArgs();
        for (Object param : params) {
            StringBuilder str = new StringBuilder(param.toString());
            token = str.substring(str.indexOf("(") + 1, str.indexOf(")")).split(",")[0].split("=")[1];
        }
        if (Objects.equals(token, accessToken) || !authorize.required()) {
            return joinPoint.proceed();
        } else if (accessToken == null || Objects.equals(accessToken, "")) {
            return ResultUtils.error(500, "Token过期,请重新登录", "Internal error");
        } else if (!Objects.equals(token, accessToken)) {
            return ResultUtils.error(501, "验证失败", "Internal error");
        }
        return joinPoint.proceed();
    }
}
