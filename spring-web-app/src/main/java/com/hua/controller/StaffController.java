package com.hua.controller;

import com.hua.mapper.MarkMapper;
import com.hua.mapper.StaffMapper;
import com.hua.pojo.Staff;
import com.hua.pojo.StaffMark;
import com.hua.pojo.StaffView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    MarkMapper markMapper ;
    @Autowired
    StaffMapper staffMapper ;
    @GetMapping("/recruit-list")
    public String staffListAll(Model model){
        List<StaffView> viewList = markMapper.innerQueryStaffView();
        model.addAttribute("staffs",viewList);
        return "staff/recruit-list";
    }
    @GetMapping("/recruit-update/{email}")
    public String staffUpdate(Model model, @PathVariable("email") String email){
        Staff staff = staffMapper.getStaffByEmail(email) ;
        model.addAttribute("staff",staff) ;
        return "staff/recruit-update";
    }
}
