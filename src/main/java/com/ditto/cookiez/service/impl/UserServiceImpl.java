package com.ditto.cookiez.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ditto.cookiez.auth.JwtTokenUtil;
import com.ditto.cookiez.entity.User;
import com.ditto.cookiez.mapper.UserMapper;
import com.ditto.cookiez.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private QueryWrapper<User> wrapper = new QueryWrapper();
    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired
    private UserServiceImpl userServiceImp;
    private final QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public User getByUsername(String username) {
        wrapper.clear();
        wrapper.eq("username", username);
        return getOne(wrapper);
    }

    @Override
    public User auth(String username, String password) {

        User user = getByUsername(username);
        if (user != null && user.getUserPwd().equals(password)) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            final String token = jwtTokenUtil.generateToken(userDetails);
            user.setAccessToken(token);
            return user;
        }
        return null;
    }
}
