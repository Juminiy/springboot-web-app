package com.hua.data.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jdbc")
public class JdbcController {
    @Autowired
    JdbcTemplate jdbcTemplate ;

    @GetMapping("/query/userList")
    public List<Map<String,Object>> getAllUsers(){
        String sqlWord = "SELECT * FROM admin" ;
        return jdbcTemplate.queryForList(sqlWord);
    }
    @GetMapping("/add/user")
    public String addUser(){
        String sqlWord = "insert into security.admin(username, password) VALUE ('Golang','goland')" ;
        jdbcTemplate.execute(sqlWord);
        return  "add ok !" ;
    }

    @GetMapping("/delete/user/{username}/{password}")
    public String deleteUser(@PathVariable("username") String username,@PathVariable("password") String password){
        String sqlWord = "delete from security.admin where username = '"+username+"' && password = '"+ password +"'";
        jdbcTemplate.execute(sqlWord);
        return  "delete ok !" ;
    }

    @GetMapping("/update/user")
    public String updateUser(){
        String sqlWord = "update security.admin set username='kps',password='123456' where username='Redis'" ;
        jdbcTemplate.execute(sqlWord);
        return  "update ok !" ;
    }
}
