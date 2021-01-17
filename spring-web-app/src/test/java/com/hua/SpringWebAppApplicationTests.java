package com.hua;

import com.hua.mapper.DepartmentMapper;
import com.hua.mapper.EmployeeMapper;
import com.hua.pojo.Department;
import com.hua.pojo.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
public class SpringWebAppApplicationTests {
    @Autowired
    DataSource dataSource ;
    @Test
    void contextLoads() throws SQLException {
        Connection connection = dataSource.getConnection() ;
        System.out.println(connection.getClass());
        connection.close();
    }
    @Autowired
    DepartmentMapper departmentMapper ;
    @Test
    void deptTestAll(){
        List<Department> departments = departmentMapper.getDepartments();
        for(Department department :departments){
            System.out.println(department.toString());
        }
    }
    @Test
    void deptTestGet(){
        System.out.println(departmentMapper.getDepartmentById(101));
    }
    @Autowired
    EmployeeMapper employeeMapper ;
    @Test
    void emplTestAll(){
        List<Employee> employees =  employeeMapper.getAll();
        for(Employee employee:employees){
            System.out.println(employee.toString());
        }
    }
    @Test
    void emplTestAdd(){
        employeeMapper.add(new Employee(2,"kkk03","hln@kv.com",1,new Date(2000,01,01),102));
    }
    @Test
    void emplTestDelete(){
        employeeMapper.delete(1);
    }
    @Test
    void emplTestGet(){
        System.out.println(employeeMapper.getEmployeeById(2));
    }
    @Test
    void emplTestUpdate(){
        employeeMapper.update(new Employee(2,"KPS03","hln@kv.com",1,Date.valueOf("2001-02-09"),103));
    }
}
