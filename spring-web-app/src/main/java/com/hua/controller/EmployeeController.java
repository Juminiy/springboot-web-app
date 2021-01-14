package com.hua.controller;

import com.hua.dao.DepartmentDao;
import com.hua.dao.EmployeeDao;
import com.hua.pojo.Department;
import com.hua.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("/employ")
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDap ;
    @Autowired
    DepartmentDao departmentDao ;

    @GetMapping("/listAll")
    public String getAllEmployees(Model model){
        Collection <Employee> employees = employeeDap.getAll();
        model.addAttribute("employees",employees) ;
        return "/employee/list";
    }
    @GetMapping("/add")
    public String addPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments) ;
        return "/employee/add";
    }
    @PostMapping("/add")
    public String addEmployee(Employee employee){
        employeeDap.save(employee);
        return "redirect:/employ/listAll";
    }
}
