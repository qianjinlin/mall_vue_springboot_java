package com.example.demo.entry;

import lombok.Data;
import lombok.ToString;

/**
 * @author qianjinlin
 * 与前端对接的UserEntry
 */
@Data
@ToString
public class UserEntry {
    private int id;
    private String username;
    private String password;
    private String email;
    private String cellphone;
    private int state;
    private String createTime;
    private String updateTime;
    private String type;
    private String typeName;
    private String statusName;

}
