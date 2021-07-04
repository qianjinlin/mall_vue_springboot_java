package com.example.demo.service.serviceimpl;

import com.example.demo.bean.UserBean;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.utils.RedisUtil;
import com.example.demo.utils.Result;
import com.example.demo.utils.ResultUtils;
import com.example.demo.utils.TokenUtils;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author 29273
 */
@Service
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    public UserMapper userMapper;
    @Resource
    private RedisUtil redisUtil;
    /**
     * [登录实现类]
     * @param username
     * @param password
     * @return com.example.demo.bean.UserBean
     * @author qianjinlin
     * @date 2021/5/16 12:56
     */
    @Override
    public Result<Object> loginIn(String username, String password) {
        String tokenId = "token";
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            UserBean userBean = userMapper.getUserInfo(username, password);
            if (userBean != null) {
                if(redisUtil.get(tokenId) != null){
                    System.err.println("redis==="+redisUtil.get(tokenId));
                    return ResultUtils.error(250,"请勿重复登录","");
                }else{
                    String token = TokenUtils.token(username, password);
                    redisUtil.setEx(tokenId, token, 100, TimeUnit.MINUTES);
                    System.out.println(redisUtil.get("token"));
                    return ResultUtils.loginSuccess(token);
                }
            } else {
                return ResultUtils.error(400,"用户名或者密码错误","");
            }
        }else{
            return ResultUtils.error(500,"登录失败","");
        }

    }
    /**
     * [用户登出]
     * @param token
     * @return com.example.demo.utils.Result<java.lang.Object>
     * @author qianjinlin
     * @date 2021/6/6 16:21
     */
    public Result<Object> loginOut(String token, HttpServletRequest request){
        //强行获取redisUtils的bean
        RedisUtil redisUtil = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()).getBean(RedisUtil.class);
        if(StringUtils.isEmpty(token)){
            return ResultUtils.error(520,"没有Token","");
        }else{
            String accessToken  = (String) redisUtil.get("token");
            if(Objects.equals(accessToken, token)){
                System.out.println("token删除");
                redisUtil.delete("token");
                return ResultUtils.success("退出登录成功","");
            }else {
                return ResultUtils.error(401,"Token不存在","");
            }
        }
    }

    @Override
    public UserBean getAllUserList() {
        return userMapper.getAllUserList();
    }
}
