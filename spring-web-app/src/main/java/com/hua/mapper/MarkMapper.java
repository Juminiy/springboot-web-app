package com.hua.mapper;

import com.hua.pojo.StaffMark;
import com.hua.pojo.StaffView;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MarkMapper {
    List<StaffMark> getAllMark();
    StaffMark getMarkByEmail(String email);
    void addMark(StaffMark staffMark);
    List<StaffView> innerQueryStaffView();
}
