package com.example.demo.utils;

/**
 * @program: SpringBoot_DI
 * @description: test
 * @author: 钱金林
 * @create: 2021-05-16 16:07
 **/

import com.example.demo.bean.UserBean;
import com.example.demo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApplication.class)
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testQuery() {
        UserBean user = userMapper.selectByPrimaryKey(1L);
        System.out.println(user);
    }

}

