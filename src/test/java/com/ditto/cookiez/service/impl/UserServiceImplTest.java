package com.ditto.cookiez.service.impl;

import com.ditto.cookiez.entity.User;
import com.ditto.cookiez.mapper.UserMapper;
import com.ditto.cookiez.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zhihao Liang
 * @date 2020/9/9 22:08
 * @email s3798366@student.rmit.edu.au
 */
@Slf4j
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    UserServiceImpl service ;
    @Test
    public void test() {

        System.out.println(service.getByUsername("123"));
    }

    @Test
    void getByUsername() {


        System.out.println(("----- selectAll method test ------"));

        System.out.println(userMapper.selectById(1).toString());

    }
}