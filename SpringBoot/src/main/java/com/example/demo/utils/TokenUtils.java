package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: SpringBoot_DI
 * @description: 登录Token验证
 * @author: 钱金林
 * @create: 2021-05-16 13:24
 **/

public class TokenUtils {

    private static final long EXPIRE_DATE = 30 * 60 * 100000;
    private static final String token_secret = "ZCfasfhuaUUHufguGuwu2020BQWE";

    /**
     * [生成Token]
     *
     * @param username
     * @param password
     * @return java.lang.String
     * @author qianjinlin
     * @date 2021/5/16 13:32
     */


    public static  String token(String username, String password) {
        String token = "";
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            Algorithm algorithm = Algorithm.HMAC256(token_secret);
            Map<String, Object> header = new HashMap<String, Object>();
            header.put("typ", "JWT");
            token = JWT.create().
                    withHeader(header).
                    withClaim("username", username)
                    .withClaim("password", password).withExpiresAt(date).sign(algorithm);
            return token;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * [验证token]
     *
     * @param token
     * @return
     * @author qianjinlin
     * @date 2021/5/16 13:32
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(token_secret);
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT jwt = jwtVerifier.verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }



}
