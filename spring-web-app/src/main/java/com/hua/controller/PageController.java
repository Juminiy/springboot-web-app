package com.hua.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {
    @GetMapping("/news")
    public String news(){
        return "page/news";
    }
    @GetMapping("/products")
    public String products(){
        return "page/products";
    }
    @GetMapping("/file")
    public String file(){
        return "page/file" ;
    }
}
