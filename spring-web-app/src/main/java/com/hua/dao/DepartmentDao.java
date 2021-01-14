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
        departmentMap.put(1001,new Department(1001,"编译原理组"));
        departmentMap.put(1002,new Department(1002,"应用开发"));
        departmentMap.put(1003,new Department(1003,"前端开发"));
        departmentMap.put(1004,new Department(1004,"后端开发"));
        departmentMap.put(1005,new Department(1005,"操作系统组"));
    }

    public Collection<Department> getDepartments(){
        return departmentMap.values();
    }
    public Department getDepartmentById(Integer id ){
        return departmentMap.get(id);
    }
}
