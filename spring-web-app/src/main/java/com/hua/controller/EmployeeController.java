package com.hua.controller;

import com.hua.dao.EmployeeDao;
import com.hua.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDap ;
    @RequestMapping("/employ/listAll")
    public String getAllEmployees(Model model){
        Collection <Employee> employees = employeeDap.getAll();
        model.addAttribute("employees","employees") ;
        return "/employee/list";
    }
}
