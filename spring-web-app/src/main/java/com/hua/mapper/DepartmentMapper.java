package com.hua.mapper;

import com.hua.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DepartmentMapper {
    List<Department> getDepartments();
    Department getDepartmentById(Integer id );
}
