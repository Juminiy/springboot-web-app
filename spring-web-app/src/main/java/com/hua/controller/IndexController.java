package com.hua.controller;

import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class IndexController {
    @GetMapping({"/signin"})
    public String signin(){
        return "signin";
    }
    @GetMapping({"/main","/index","/"})
    public String index(){
        return "dashboard";
    }
}
