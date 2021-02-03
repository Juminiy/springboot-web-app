package com.hua.esjd.controller;


import com.hua.esjd.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class ContentController {
    @Autowired
    ContentService contentService ;

    @GetMapping("/index/create/{index}")
    public boolean createIndex(@PathVariable("index")String index) throws IOException {
        return  contentService.createIndex(index);
    }
    @GetMapping("/index/delete/{index}")
    public boolean deleteIndex(@PathVariable("index")String index) throws IOException {
        return  contentService.deleteIndex(index);
    }
    @GetMapping("/parse/{keyword}")
    public boolean parse(@PathVariable("keyword") String keyword) throws IOException {
        return contentService.parseContent(keyword) ;
    }
}
