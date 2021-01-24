package com.hua.tasks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class SpringTasksApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSender ;

    // 简单邮件
    @Test
    void contextLoads() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("通知，这是测试邮件");
        mailMessage.setText("没有BUG");

        mailMessage.setTo("hln0x29a@gmail.com");
        mailMessage.setFrom("2285151098@qq.com");
        javaMailSender.send(mailMessage);
    }

    @Test
    void complexMail() throws MessagingException {
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
