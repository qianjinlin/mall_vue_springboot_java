package com.example.demo.mapper;

import com.example.demo.bean.UserBean;
import com.example.demo.entry.UserEntry;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 29273
 */
@Component
public interface UserMapper {
    /**
     * [登录]
     *
     * @param username
     * @param password
     * @return com.example.demo.bean.UserBean
     * @author qianjinlin
     * @date 2021/5/15 18:49
     */
    UserBean getUserInfo(String username, String password);

    /**
     * [获取用户信息列表]
     *
     * @param
     * @return java.util.List
     * @author qianjinlin
     * @date 2021/5/22 12:00
     */
    List<UserEntry> getAllUserList();


    /**
     * [更新用户信息]
     *
     * @param
     * @return java.util.List<com.example.demo.entry.UserEntry>
     * @author qianjinlin
     * @date 2021/7/10 19:34
     */
    void updateUser(final UserBean userBean);

}
