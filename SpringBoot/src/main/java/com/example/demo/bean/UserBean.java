package com.example.demo.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author qianjinlin
 */
@Data
@Table(name = "user")
public class UserBean {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private String username;
    private String password;
    private String email;
    private String cellphone;
    private int state;
    @Transient
    private String createTime;
    @Transient
    private String updateTime;
    private String type;

}
