package com.ditto.cookiez.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ditto.cookiez.WebLogAspect;
import com.ditto.cookiez.auth.JwtTokenUtil;
import com.ditto.cookiez.entity.User;
import com.ditto.cookiez.mapper.UserMapper;
import com.ditto.cookiez.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private UserMapper userMapper;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public User getByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        List<User> list = userMapper.selectList(wrapper);
        User user = list.get(0);
        return user;
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

    @Override
    public User getUserByToken(String token) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = null;
        if (username != null) {
            user = getByUsername(username);
        }
        return user;
    }

    @Override
    public Boolean register(User user) {
//        Has existed the user?
        User qUser = getByUsername(user.getUsername());
        if (qUser == null) {
            save(user);
            return true;
        }
        return false;
    }
}
