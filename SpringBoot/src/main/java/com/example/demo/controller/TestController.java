package com.example.demo.controller;

import com.example.demo.annotation.Authorize;
import com.example.demo.bean.UserBean;
import com.example.demo.service.UserService;
import com.example.demo.service.serviceimpl.UserServiceImpl;
import com.example.demo.utils.Result;
import com.example.demo.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


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
        return new UserServiceImpl().loginOut(token, request);
    }


    @Authorize
    @RequestMapping("/getAllUserInfo")
    public Result<Object> getUserList(@RequestHeader HttpHeaders headers) {
        return ResultUtils.getDataSuccess(userService.getAllUserList());
    }

    @Authorize
    @RequestMapping("/updateUser")
    public boolean updateUser(UserBean userBean, @RequestHeader HttpHeaders headers) {
        UserBean user = new UserBean();
        user.setId(userBean.getId());
        user.setType(userBean.getType());
        try {
            userService.updateUser(userBean);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @RequestMapping("/query/{id}")
    public Result<Object> queryById(@PathVariable("id") Integer id) {
        return ResultUtils.getDataSuccess(userService.queryById(id));

    }


}
