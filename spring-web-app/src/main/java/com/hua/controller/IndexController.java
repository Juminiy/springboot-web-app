package com.hua.controller;

import com.hua.mapper.StaffMapper;
import com.hua.pojo.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    StaffMapper staffMapper ;
    @GetMapping({"/signin"})
    public String signin(){
        return "signin";
    }
    @GetMapping({"/main","/index","/"})
    public String index(Model model, Authentication authentication){
        if(authentication!=null){
            String email = authentication.getName() ;
            //System.out.println(email);
            if(email != null){
                Staff isStaff = staffMapper.getStaffByEmail(email) ;
                if(isStaff != null){
                    model.addAttribute("user",isStaff.getNickName());
                }else{
                    String AdminNick = email.replaceAll("@2084team.cn","");
                    model.addAttribute("user",AdminNick) ;
                }
            }else{
                model.addAttribute("user","2084er");
            }
        }else{
            model.addAttribute("user","2084er") ;
        }
        return "dashboard" ;
    }
}
