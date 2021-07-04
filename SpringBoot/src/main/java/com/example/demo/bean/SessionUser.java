package com.example.demo.bean;

import lombok.Data;

/**
 * @program: SpringBoot_DI
 * @description: 用户缓存
 * @author: 钱金林
 * @create: 2021-05-16 15:48
 **/
@Data
public class SessionUser {
    private String token;
    private String username;
}
