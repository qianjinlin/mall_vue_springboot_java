package com.example.demo;

import com.example.demo.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * @author 29273
 */
@SpringBootApplication
@MapperScan("com.example.demo.Mapper")
@Slf4j
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @PostConstruct
    public void getRedis(){
        boolean flag = new RedisUtil().getRedisIsOk("127.0.0.1",6379);
        if (flag) {
            log.info("=============redis已成功部署==============");
        }
    }
}


