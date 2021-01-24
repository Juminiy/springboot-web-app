package com.hua.security.service;

import com.hua.security.mapper.GithubUserMapper;
import com.hua.security.pojo.githubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    GithubUserMapper githubUserMapper ;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        githubUser user = githubUserMapper.getByUsername(s) ;
        List<GrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_vip1"));
        authorities.add(new SimpleGrantedAuthority("ROLE_vip2"));
        authorities.add(new SimpleGrantedAuthority("ROLE_vip3"));
        String uuid = user.getUuid();
        String username = user.getUsername();
        if(username.equals(s)){
            return new User(username,bCryptPasswordEncoder.encode(uuid),authorities);
        }else{
            return null;
        }
    }
}
