package com.hua.mybatis;

import com.hua.mybatis.mapper.UserMapper;
import com.hua.mybatis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class SpringMybatisApplicationTests {

    @Autowired
    DataSource dataSource ;
    @Test
    void contextLoads() throws SQLException {
        Connection connection = dataSource.getConnection() ;
        System.out.println(connection.getClass());
        connection.close();
    }

    @Autowired
    private UserMapper userMapper ;
    @Test
    void userTestListAll(){
        List<User> users = userMapper.queryUserList();
        for(User user : users){
            System.out.println(user.toString());
        }
    }
    @Test
    void userTestQuery(){
        User user = userMapper.queryUserById(2);
        System.out.println(user.toString());
    }
    @Test
    void userTestDelete(){
        userMapper.deleteUser(1);
    }
    @Test
    void userTestAdd(){
        userMapper.addUser(new User(2,"kps","123456"));
    }
}
