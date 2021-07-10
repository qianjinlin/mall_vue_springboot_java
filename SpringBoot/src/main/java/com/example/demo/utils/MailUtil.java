package com.example.demo.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * @program: SpringBoot_DI
 * @description: 邮件处理类
 * @author: 钱金林
 * @create: 2021-07-10 11:22
 **/

public class MailUtil {
    public static boolean sendQQMail(String toEmail,String subject,String msg){
        HtmlEmail email = new HtmlEmail();
        email.setDebug(true);
        email.setHostName("SMTP.qq.com");
        email.setSmtpPort(587);
        email.setAuthentication("2927362791@qq.com","dnzskfxqqyzodchb");
        try {
            email.setFrom("2927362791@qq.com");
            email.addTo(toEmail);
            email.setMsg(msg);
            email.setSubject(subject);
            email.send();
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return false;
    }
}
