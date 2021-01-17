package com.hua.controller;

import com.hua.mapper.DepartmentMapper;
import com.hua.mapper.EmployeeMapper;
import com.hua.pojo.Department;
import com.hua.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collection;


@Controller
@RequestMapping("/employ")
public class EmployeeController {
    @Autowired
    EmployeeMapper employeeDao ;
    @Autowired
    DepartmentMapper departmentDao ;

    @GetMapping("/listAll")
    public String getAllEmployees(Model model){
        Collection <Employee> employees = employeeDao.getAll();
        model.addAttribute("employees",employees) ;
        model.addAttribute("departments",departmentDao.getDepartments());
        return "employee/list";
    }
    @GetMapping("/add")
    public String addPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments) ;
        return "employee/add";
    }
    @PostMapping("/add")
    public String addEmployee(Employee employee,String birth){
        employee.setBirth(Date.valueOf(birth));
        employeeDao.add(employee);
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
        employeeDao.update(employee);
        return "redirect:/employ/listAll" ;
    }
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/employ/listAll";
    }
}
