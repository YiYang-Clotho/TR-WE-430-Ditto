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

//     User user=  service.getByUsername("1234");
//     user.setUsername("123");
//     user.setUserPwd(null);
//     user.updateById();

    }

    @Test
    void getByUsername() {



    }
}