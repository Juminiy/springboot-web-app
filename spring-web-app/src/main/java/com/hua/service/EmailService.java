package com.hua.service;


import com.hua.utils.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    JavaMailSenderImpl mailSender;
    /**
     *
     * @instruction the text code is deadly
     * @throws MessagingException
     * @Author Juminiy
     */

    public void Subscribe(String UserEmail) throws MessagingException{
        MimeMessage mimeMessage=mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        helper.setSubject(EmailMessage.Default_Subject);
        StringBuilder sb= new StringBuilder("")
                .append(EmailMessage.Default_Word).append(" , ")
                .append(EmailMessage.Default_Thanks).append(" !");

        helper.setText(sb.toString());
        helper.setText(EmailMessage.Default_html_image,sb.toString());
        helper.setFrom(EmailMessage.Default_Email);
        helper.setTo(UserEmail);

        mailSender.send(mimeMessage);
    }

    public void RegisterCode(String UserEmail,String code) throws MessagingException {
        MimeMessage mimeMessage=mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        helper.setSubject(EmailMessage.Default_Subject);
        StringBuilder sb= new StringBuilder("")
                .append(EmailMessage.First_Register).append(",")
                .append(EmailMessage.Default_Message).append("  \" ")
                .append(code).append(" \" ")
                .append(EmailMessage.TIMESTAMP).append(",")
                .append(EmailMessage.Default_Telling).append(" !");

        helper.setText(sb.toString());

        System.out.println(sb.toString()+UserEmail);

        helper.setFrom(EmailMessage.Default_Email);
        helper.setTo(UserEmail);

        mailSender.send(mimeMessage);
    }

    public void FindCode(String UserEmail,String code) throws MessagingException {
        MimeMessage mimeMessage=mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        helper.setSubject(EmailMessage.Default_Subject);
        StringBuilder sb= new StringBuilder("")
                .append(EmailMessage.Find_Secret).append(",")
                .append(EmailMessage.Default_Message).append("  \" ")
                .append(code).append(" \" ")
                .append(EmailMessage.TIMESTAMP).append(",")
                .append(EmailMessage.Care_Telling2).append(": \" ")
                .append(EmailMessage.Default_Telling2).append(" !");

        helper.setText(sb.toString());

        System.out.println(sb.toString()+UserEmail);

        helper.setFrom(EmailMessage.Default_Email);
        helper.setTo(UserEmail);

        mailSender.send(mimeMessage);
    }

}
