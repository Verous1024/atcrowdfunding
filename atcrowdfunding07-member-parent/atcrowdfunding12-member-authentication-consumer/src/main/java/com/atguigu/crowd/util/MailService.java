package com.atguigu.crowd.util;

/**
 * 邮件服务接口
 * Created by ASUS on 2018/5/5
 *
 * @Authod Grey Wolf
 */
public interface MailService {
 
    /**
     * 发送简单邮件
     * @param to
     * @param subject
     * @param content
     */
    void sendMail(String to,String subject,String content);
    void sendHtmlMail(String to,String subject,String content);
}