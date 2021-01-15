package com.hua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/user")
public class LoginController {

    @RequestMapping("/login")
    public String login(@RequestParam("email") String email ,
                        @RequestParam("password") String password ,
                        Model model, HttpServletRequest request){
        if(!StringUtils.isEmpty(email) && password.equals("123456")){
            request.getSession().setAttribute("loginUser",email.substring(0,3));
            return "redirect:/main" ;
        }else{
            model.addAttribute("msg","用户名或者密码错误!");
            return "signin" ;
        }

    }
    @GetMapping("/logout")
    public String logout(Model model,HttpServletRequest request){
        model.addAttribute("msg","已注销,请重新登录!") ;
        request.getSession().removeAttribute("loginUser");
        return "signin";
    }
}
