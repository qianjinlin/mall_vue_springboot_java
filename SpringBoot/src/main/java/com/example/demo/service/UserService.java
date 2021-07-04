package com.example.demo.service;


import com.example.demo.bean.UserBean;
import com.example.demo.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 29273
 */
@Service
public interface UserService {

    /**
     * [用户登录]
     *
     * @param username
     * @param password
     * @return com.example.demo.bean.UserBean
     * @author qianjinlin
     * @date 2021/5/15 18:47
     */
    Result loginIn(String username, String password);

    /**
     * [获取用户列表]
     * @param
     * @return java.util.List
     * @author qianjinlin
     * @date 2021/5/22 12:02
     */


    UserBean getAllUserList();


}
