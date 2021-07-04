package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @program: SpringBoot_DI
 * @description: Redis配置类
 * @author: 钱金林
 * @create: 2021-05-16 13:02
 **/
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<Object,Object> stringRedisTemplate;

    public boolean getRedisIsOk(String url,int port){
        boolean flag = true;

        try {
            Jedis jedis = new Jedis(url,port);
            String ping = jedis.ping();
            if(ping.equalsIgnoreCase("PONG")){
                System.out.println("redis已成功部署");
                flag = true;
            }else{
                flag = false;
            }
        }catch (Exception e){
            System.out.println("redis连接不可用");
        }

        return flag;
    }

    public void setStringRedisTemplate(RedisTemplate<Object, Object> stringRedisTemplate) {
        stringRedisTemplate.setEnableTransactionSupport(false);
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public RedisTemplate getStringRedisTemplate() {
        return stringRedisTemplate;
    }


    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    public void delete(Collection<String> keys) {
        stringRedisTemplate.delete(keys);
    }

    public byte[] dump(String key) {
        return stringRedisTemplate.dump(key);
    }

    public  boolean hasKey(String key){
        return  stringRedisTemplate.hasKey(key);
    }

    public Boolean expire(String key, long timeout, TimeUnit timeUnit){
        return stringRedisTemplate.expire(key,timeout,timeUnit);
    }

    public Boolean expireAt(String key, Date date) {
        return stringRedisTemplate.expireAt(key, date);
    }

    public Object randomKey() {
        return stringRedisTemplate.randomKey();
    }

    public void rename(String oldKey, String newKey) {
        stringRedisTemplate.rename(oldKey, newKey);
    }

    public Boolean renameIfAbsent(String oldKey, String newKey) {
        return stringRedisTemplate.renameIfAbsent(oldKey, newKey);
    }

    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key) {
        return key == null?null:stringRedisTemplate.opsForValue().get(key);
    }

    public void setEx(String key, String value, long timeout, TimeUnit unit) {
        stringRedisTemplate.setEnableTransactionSupport(false);
        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }









}
