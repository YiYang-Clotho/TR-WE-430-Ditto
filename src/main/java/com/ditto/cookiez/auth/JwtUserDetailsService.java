package com.ditto.cookiez.auth;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.ditto.cookiez.entity.SecurityUserDetails;
import com.ditto.cookiez.entity.User;
import com.ditto.cookiez.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserServiceImpl userService;
//    get user data from database
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("JwtUserDetailsService:" + username);
        List<GrantedAuthority> authorityList = new ArrayList<>();
//        from database to get role
        authorityList.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        User user= userService.getByUsername(username);
        System.out.println(user);
        return new SecurityUserDetails(user.getUsername(),user.getUserPwd(),authorityList);
    }

}