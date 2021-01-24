package com.hua.security.mapper;


import com.hua.security.pojo.githubUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GithubUserMapper {
    githubUser getByUsername(String username);
    void updateToken(String username,String token);
}
