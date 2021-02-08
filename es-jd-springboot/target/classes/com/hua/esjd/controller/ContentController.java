package com.hua.esjd.controller;


import com.hua.esjd.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ContentController {
    @Autowired
    ContentService contentService ;

    @GetMapping("/index/create/{index}")
    public String createIndex(@PathVariable("index")String index) throws IOException {
        return  String.valueOf(contentService.createIndex(index));
    }
    @GetMapping("/index/delete/{index}")
    public String deleteIndex(@PathVariable("index")String index) throws IOException {
        return  String.valueOf(contentService.deleteIndex(index));
    }
    @GetMapping("/parse/{keyword}")
    public String parse(@PathVariable("keyword") String keyword) throws IOException {
        return String.valueOf(contentService.parseContent(keyword)) ;
    }
    @GetMapping("/search/{index}/{keyword}/{pageNumber}/{pageSize}")
    public ArrayList<Map<String,Object>> search(@PathVariable("index")String index,
                                                @PathVariable("keyword")String keyword,
                                                @PathVariable("pageNumber") int pageNumber,
                                                @PathVariable("pageSize")int pageSize) throws IOException {
        return contentService.search(index,keyword,pageNumber,pageSize) ;
    }
    @GetMapping("/search/highLight/{index}/{keyword}/{pageNumber}/{pageSize}")
    public ArrayList<Map<String,Object>> searchHighLight(@PathVariable("index")String index,
                                                @PathVariable("keyword")String keyword,
                                                @PathVariable("pageNumber") int pageNumber,
                                                @PathVariable("pageSize")int pageSize) throws IOException {
        return contentService.searchHighLight(index,keyword,pageNumber,pageSize) ;
    }
}
