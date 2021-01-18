package com.hua.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {
    @GetMapping({"/","index"})
    public String index(){
        return "index";
    }
    @GetMapping("/login")
    public String login(){
        return "views/login";
    }
    @GetMapping("/level/{id}")
    public String level1(@PathVariable("id") int id){
        return "views/level1"+id;
    }
    @GetMapping("/level2/{id}")
    public String level2(@PathVariable("id") int id){
        return "views/level2"+id;
    }
    @GetMapping("/level3/{id}")
    public String level3(@PathVariable("id") int id){
        return "views/level3"+id;
    }
}
