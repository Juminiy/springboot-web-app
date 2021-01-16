package com.hua.mybatis.controller;

import com.hua.mybatis.mapper.UserMapper;
import com.hua.mybatis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper ;

    @GetMapping("/getAllUsers")
    public List<User> queryUserList(){
        List<User> users = userMapper.queryUserList();
        for(User user : users){
            System.out.println(user.toString());
        }
        return users ;
    }
}
