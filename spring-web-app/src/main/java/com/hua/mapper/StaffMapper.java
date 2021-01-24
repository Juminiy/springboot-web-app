package com.hua.mapper;


import com.hua.pojo.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StaffMapper {
    List<Staff> getAllStaff();
    Staff getStaffByEmail(String email);
    void addStaff(Staff staff) ;
}
