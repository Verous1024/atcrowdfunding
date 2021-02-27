package com.atguigu.crowd.util;


import com.atguigu.crowd.handler.MemberHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 *
 * 邮件服务类
 * Created by ASUS on 2018/5/5
 *
 * @Authod Grey Wolf
 */
 
@Service("mailService")
public class MailServiceImpl implements MailService {
 
    @Autowired
    private JavaMailSender mailSender;
    private Logger logger = LoggerFactory.getLogger(MemberHandler.class);

    private String form = "pd18290259402@163.com";
    /**
     * 发送简单邮件
     * @param to 接受者
     * @param subject 主题
     * @param content 内容
     */
    @Override
    public void sendMail(String to, String subject, String content) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom(form);//发起者
        mailMessage.setTo(to);//接受者
        //如果发给多个人的：
        //mailMessage.setTo("1xx.com","2xx.com","3xx.com");
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        try {
            mailSender.send(mailMessage);
            logger.info("发送简单邮件成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("发送简单邮件失败");
        }
    }

    @Override
    public boolean sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(form);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true); //开启HTML格式的邮箱
            mailSender.send(message);
            logger.info("发送HTML邮件成功");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            logger.info("发送HTML邮件失败");
            return false;
        }
    }
}