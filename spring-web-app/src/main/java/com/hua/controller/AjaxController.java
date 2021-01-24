package com.hua.controller;

import com.hua.service.EmailService;
import com.hua.utils.EmailCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/ajax")
@RestController
public class AjaxController {

    @Autowired
    EmailService emailService ;
    @RequestMapping(value = "/postQicqEmail",produces = "application/json;charset=UTF-8")
    public void  postQicqEmail(@RequestBody String QicqNumber, HttpServletResponse response) throws IOException, MessagingException {
        response.setContentType("text/text");
        response.setCharacterEncoding("UTF-8");
        if(QicqNumber != null ){
            StringBuffer email = new StringBuffer(QicqNumber.replaceAll("\\D",""));
            email.append("@qq.com");
            System.out.println(email);
            emailService.Subscribe(email.toString());
            response.getWriter().print("200");
        }else{
            response.getWriter().print("400");
        }
    }
    @RequestMapping(value = "/registerCode",produces = "application/json;charset=UTF-8")
    public void  postRegisterCode(@RequestBody String responseContent, HttpServletResponse response) throws IOException, MessagingException {
        response.setContentType("text/text");
        response.setCharacterEncoding("UTF-8");
        if(responseContent != null ){
            String code = EmailCodeUtils.getNumber();
            String email = responseContent.replace("responseContent=","").replace("%40","@");
            emailService.RegisterCode(email,code);
            response.getWriter().print(code);
        }else{
            response.getWriter().print("400");
        }
    }
}
