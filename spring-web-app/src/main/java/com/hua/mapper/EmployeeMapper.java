package com.hua.mapper;


import com.hua.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmployeeMapper {
    List<Employee> getAll();
    void add(Employee employee);
    void delete(int id);
    void update(Employee employee);
    Employee getEmployeeById(int id);
}
