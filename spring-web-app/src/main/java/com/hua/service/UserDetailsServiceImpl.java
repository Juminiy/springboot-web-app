package com.hua.service;

import com.hua.mapper.StaffMapper;
import com.hua.pojo.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    StaffMapper staffMapper ;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Staff staff = staffMapper.getStaffByEmail(s) ;
        if(staff==null){
            return null ;
        }else{
            Collection<GrantedAuthority> authorityGranters = new ArrayList<>();
            authorityGranters.add(new SimpleGrantedAuthority("ROLE_student"));
            authorityGranters.add(new SimpleGrantedAuthority("ROLE_staff"));
            if(staff.getEmail().equals("hln0x29a@gmail.com")){
                authorityGranters.add(new SimpleGrantedAuthority("ROLE_admin"));
            }
            return new User(staff.getEmail(),bCryptPasswordEncoder.encode(staff.getWorkName()),authorityGranters);
        }
    }
}
