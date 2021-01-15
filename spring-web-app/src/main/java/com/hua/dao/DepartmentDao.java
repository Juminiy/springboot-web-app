package com.hua.dao;

import com.hua.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentDao {
    // 模拟数据库
    private static Map<Integer, Department> departmentMap = null ;
    static {
        departmentMap = new HashMap<>();
        departmentMap.put(101,new Department(101,"编译原理组"));
        departmentMap.put(102,new Department(102,"应用开发"));
        departmentMap.put(103,new Department(103,"前端开发"));
        departmentMap.put(104,new Department(104,"后端开发"));
        departmentMap.put(105,new Department(105,"操作系统组"));
    }

    public Collection<Department> getDepartments(){
        return departmentMap.values();
    }
    public Department getDepartmentById(Integer id ){
        return departmentMap.get(id);
    }
}
