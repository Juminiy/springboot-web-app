package com.hua.controller;

import com.hua.mapper.DepartmentMapper;
import com.hua.mapper.MarkMapper;
import com.hua.mapper.StaffMapper;
import com.hua.pojo.Staff;
import com.hua.pojo.StaffView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    MarkMapper markMapper ;
    @Autowired
    StaffMapper staffMapper ;
    @Autowired
    DepartmentMapper departmentMapper ;
    @GetMapping("/recruit-list")
    public String staffListAll(Model model, Authentication authentication){
        if(authentication!=null){
            model.addAttribute("user",staffMapper.getStaffByEmail(authentication.getName()).getNickName());
        }
        List<StaffView> viewList = markMapper.innerQueryStaffView();
        model.addAttribute("staffs",viewList);
        return "staff/recruit-list";
    }
    @GetMapping("/recruit-update/{email}")
    public String staffUpdate(Model model, @PathVariable("email") String email){
        Staff staff = staffMapper.getStaffByEmail(email) ;
        model.addAttribute("staff",staff) ;
        model.addAttribute("departments",departmentMapper.getDepartments());
        return "staff/recruit-update";
    }
    @PostMapping("/recruit-update")
    public String updateStaff(Model model,Staff staff){
        model.addAttribute("correctSuccess","信息修改成功") ;
        staffMapper.update(staff);
        return "redirect:/index";
    }
}
