package com.hua.controller;

import com.hua.dao.DepartmentDao;
import com.hua.dao.EmployeeDao;
import com.hua.pojo.Department;
import com.hua.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@Controller
@RequestMapping("/employ")
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao ;
    @Autowired
    DepartmentDao departmentDao ;

    @GetMapping("/listAll")
    public String getAllEmployees(Model model){
        Collection <Employee> employees = employeeDao.getAll();
        model.addAttribute("employees",employees) ;
        return "employee/list";
    }
    @GetMapping("/add")
    public String addPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments) ;
        return "employee/add";
    }
    @PostMapping("/add")
    public String addEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/employ/listAll";
    }
    @GetMapping("/update/{id}")
    public String updatePage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.getEmployeeById(id) ;
        model.addAttribute("employee",employee) ;
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments) ;
        return "employee/update";
    }
    @PostMapping("/update")
    public String updateEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/employ/listAll" ;
    }
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/employ/listAll";
    }
}
