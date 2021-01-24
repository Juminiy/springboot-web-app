package com.hua.controller;

import com.hua.mapper.DepartmentMapper;
import com.hua.mapper.MarkMapper;
import com.hua.mapper.StaffMapper;
import com.hua.pojo.Staff;
import com.hua.pojo.StaffMark;
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
        List<StaffView> viewList = markMapper.innerQueryStaffView();
        model.addAttribute("staffs",viewList);
        return "staff/recruit-list";
    }
    @GetMapping("/recruit-update/{email}")
    public String staffUpdate(Model model, @PathVariable("email") String email){
        StaffMark staffMark = markMapper.getMarkByEmail(email) ;
        model.addAttribute("mark",staffMark) ;
        return "staff/recruit-update";
    }
    @PostMapping("/recruit-update")
    public String updateStaff(Model model,StaffMark staffMark){
        markMapper.updateMark(staffMark);
        model.addAttribute("updateSuccess","修改成功");
        return "redirect:/staff/recruit-list";
    }
}
