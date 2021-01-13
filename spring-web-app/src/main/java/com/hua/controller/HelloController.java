package com.hua.controller;

import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

    @RequestMapping({"/","/index.html","/signin.html","/signin","/index"})
    public String index(HttpServletRequest request){
        if(request.getSession().getAttribute("loginUser")==null){
            return "signin" ;
        }else{
            return "redirect:/db";
        }
    }
}
