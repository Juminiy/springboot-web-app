package com.hua.mybatis.mapper;

import com.hua.mybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<User> queryUserList() ;
    User queryUserById(int id) ;
    int addUser(User user) ;
    int updateUser(User user) ;
    int deleteUser(int id) ;
    @Select("select * from user where ${column} = #{value}")
    User findByColumn(@Param("column") String column, @Param("value") String value);
}
