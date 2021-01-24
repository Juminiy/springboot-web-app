package com.hua.tasks.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

public class MailSenderX {

    private String from ;
    private String to ;
    private String subject ;

    @Autowired
    JavaMailSenderImpl javaMailSender ;

    public void simpleMail(){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("通知，这是测试邮件");
        mailMessage.setText("没有BUG");

        mailMessage.setTo("hln0x29a@gmail.com");
        mailMessage.setFrom("2285151098@qq.com");
        javaMailSender.send(mailMessage);
    }

    public void htmlMail() throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper  = new MimeMessageHelper(message,true) ;

        helper.setSubject("测试邮件");
        helper.setText("<p style='color:red'>感谢学习!</p>",true);

        helper.addAttachment("god.webp",new File("C:\\Users\\Juminiy\\Pictures\\kbs\\mm.jpg"));

        helper.setFrom("2285151098@qq.com");
        helper.setTo("hln0x29a@gmail.com");
        javaMailSender.send(message);
    }

}
