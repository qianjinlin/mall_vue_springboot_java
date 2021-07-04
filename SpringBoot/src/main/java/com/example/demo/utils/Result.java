package com.example.demo.utils;

import lombok.Data;
import lombok.ToString;

/**
 * @program: SpringBoot_DI
 * @description: 数据返回
 * @author: 钱金林
 * @create: 2021-05-16 12:26
 **/
@Data
@ToString
public class Result<T> {
    private int code;
    private String msg;
    private boolean isFlag;
    private T data;
}
