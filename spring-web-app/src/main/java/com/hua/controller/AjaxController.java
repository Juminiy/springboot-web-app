package com.hua.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/ajax")
@RestController
public class AjaxController {
    @RequestMapping(value = "/postQicqEmail",produces = "application/json;charset=UTF-8")
    public void  postQicqEmail(@RequestBody String QicqNumber, HttpServletResponse response) throws IOException {
        response.setContentType("html/text");
        response.setCharacterEncoding("UTF-8");
        if(QicqNumber != null ){
            System.out.println(QicqNumber);
            response.getWriter().print("200");
        }else{
            response.getWriter().print("400");
        }
    }
}
