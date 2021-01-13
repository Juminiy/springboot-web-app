package com.hua.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/2084team")
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello 2084Team !" ;
    }

    @RequestMapping({"/index","/"})
    public String index(Model mv){
        mv.addAttribute("user","HLN");
        return "index" ;
    }
}
