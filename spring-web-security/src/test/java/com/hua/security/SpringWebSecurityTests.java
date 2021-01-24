package com.hua.security;

import com.hua.security.mapper.GithubUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringWebSecurityTests {
    @Autowired
    GithubUserMapper githubUserMapper ;
    @Test
    public void select(){
        System.out.println(githubUserMapper.getByUsername("Juminiy").toString());
    }
    @Test
    public void update(){
        githubUserMapper.updateToken("Juminiy","bfec3bba2b7d63af72153c17ffe0c897033e06e9");
    }
}
