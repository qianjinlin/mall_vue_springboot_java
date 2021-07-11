package com.example.demo.utils;

/**
 * @program: SpringBoot_DI
 * @description: 结果处理类
 * @author: 钱金林
 * @create: 2021-05-16 16:37
 **/

public class ResultUtils {

    private static final Result<Object> RES =new Result<>();

    public static Result<Object> loginSuccess(Object object){
        RES.setMsg("登录成功");
        RES.setFlag(true);
        RES.setCode(200);
        RES.setData(object);
        return RES;
    }

    public static Result<Object> error(int code, String msg, Object object){
        RES.setMsg(msg);
        RES.setFlag(false);
        RES.setCode(code);
        RES.setData(object);
        return RES;
    }

    public static Result<Object> getDataSuccess(Object data){
        RES.setMsg("获取成功");
        RES.setData(data);
        RES.setFlag(true);
        RES.setCode(200);
        return RES;
    }

    public static Result<Object> success(String msg, Object data) {
        RES.setMsg(msg);
        RES.setData(data);
        RES.setFlag(true);
        RES.setCode(200);
        return RES;
    }

    public static Result<Object> USER_NOT_FOUND() {
        RES.setCode(502);
        RES.setFlag(false);
        RES.setMsg("抱歉,查不到该用户");
        return RES;
    }


}
