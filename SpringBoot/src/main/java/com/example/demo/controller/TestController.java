package com.example.demo.controller;

import com.example.demo.bean.SessionUser;
import com.example.demo.bean.UserBean;
import com.example.demo.service.UserService;
import com.example.demo.annotation.Authorize;
import com.example.demo.service.serviceimpl.UserServiceImpl;
import com.example.demo.utils.RedisUtil;
import com.example.demo.utils.Result;
import com.example.demo.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author qianjinlin
 */
@RestController
@Slf4j
@CrossOrigin
public class TestController {

    @Autowired
    UserService userService;

    @RequestMapping("/test")
    public String getIndex(){
        return  "Test";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(UserBean userBean){
        System.out.println(userBean.getUsername());
        System.out.println(userBean.getPassword());
        return userService.loginIn(userBean.getUsername(),userBean.getPassword());
    }
    @RequestMapping(value = "/loginOut",method = RequestMethod.POST)
    public Result<Object> loginOut(String token, HttpServletRequest request){
        return new UserServiceImpl().loginOut(token,request);
    }

    @RequestMapping("/main")
    @Authorize
    public String returnToMain(SessionUser sessionUser){
        return sessionUser.getToken();
    }

    @Authorize
    @RequestMapping("/getAllUserInfo")
    public Result<Object> getUserList(SessionUser sessionUser){
        return ResultUtils.getDataSuccess(userService.getAllUserList());
    }




}
