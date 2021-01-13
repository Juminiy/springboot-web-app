package com.hua.dao;

import com.hua.pojo.Department;
import com.hua.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employeeMap = null ;
    @Autowired
    private DepartmentDao departmentDao ;
    static {
        employeeMap = new HashMap<>();
        employeeMap.put(101,new Employee(101,"json","111@gmail.com",1,new Department()));
        employeeMap.put(102,new Employee(102,"vim","121@gmail.com",0,new Department()));
        employeeMap.put(103,new Employee(103,"kiss","131@gmail.com",1,new Department()));
        employeeMap.put(104,new Employee(104,"micro","141@gmail.com",0,new Department()));
        employeeMap.put(105,new Employee(105,"less","151@gmail.com",1,new Department()));
    }

    private static Integer initId = 1006 ;
    public void save(Employee employee){
        if(employee.getId()==null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employeeMap.put(employee.getId(),employee) ;
    }

    public Collection<Employee> getAll(){
        return employeeMap.values();
    }

    public Employee getEmployeeById(Integer id ) {
        return employeeMap.get(id);
    }

    public void delete(Integer id){
        employeeMap.remove(id) ;
    }
}
