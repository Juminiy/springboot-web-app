package com.hua.controller;


import com.hua.mapper.MarkMapper;
import com.hua.pojo.StaffMark;
import com.hua.pojo.StaffView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/mark")
public class MarkController {
    @Autowired
    MarkMapper markMapper ;
    @GetMapping("/getAllMark")
    @ResponseBody
    public List<StaffMark> getAllMark(){
        return markMapper.getAllMark() ;
    }
    @GetMapping("/innerFind")
    @ResponseBody
    public List<StaffView> getStaffViews(){
        return markMapper.innerQueryStaffView();
    }

}
