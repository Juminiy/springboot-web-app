package com.hua.controller;

import com.hua.mapper.StaffMapper;
import com.hua.pojo.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SignupController {
    @Autowired
    StaffMapper staffMapper ;
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }
    @PostMapping("/signup")
    public String signupAndInfoUp(Staff staff, Model model){
        Staff staffX = staffMapper.getStaffByEmail(staff.getEmail());
        if(staffX==null){
            staffMapper.addStaff(staff);
            return "redirect:/signin";
        }else{
            model.addAttribute("hasBeenRegistered","你已经注册过了!");
            return "signup";
        }

    }



    //仅供测试
    @Deprecated
    @ResponseBody
    @GetMapping("/staff/getAll")
    public List<Staff> getAllStaff(){
        return staffMapper.getAllStaff();
    }


    @Deprecated
    @ResponseBody
    @GetMapping("/staff/getOne")
    public String getByEmail(){
        Staff staff = staffMapper.getStaffByEmail("2285151098@qq.com");
        if(staff!=null){
            return staff.toString();
        }else{
            return "null";
        }
    }

}
