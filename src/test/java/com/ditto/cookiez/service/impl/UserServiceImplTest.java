package com.ditto.cookiez.service.impl;

import com.ditto.cookiez.entity.User;
import com.ditto.cookiez.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zhihao Liang
 * @date 2020/9/9 22:08
 * @email s3798366@student.rmit.edu.au
 */

class UserServiceImplTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        UserServiceImpl service = new UserServiceImpl();
        System.out.println(service.getById(1).toString());

    }

    @Test
    void getByUsername() {


        System.out.println(("----- selectAll method test ------"));

        System.out.println(userMapper.selectById(1).toString());

    }
}