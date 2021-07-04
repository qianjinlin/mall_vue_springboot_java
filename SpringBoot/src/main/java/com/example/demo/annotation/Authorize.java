package com.example.demo.annotation;

import java.lang.annotation.*;

/**
 * @program: SpringBoot_DI
 * @description: 用户登录拦截注解
 * @author: 钱金林
 * @create: 2021-05-16 12:32
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Authorize {
    boolean required() default true;

}
