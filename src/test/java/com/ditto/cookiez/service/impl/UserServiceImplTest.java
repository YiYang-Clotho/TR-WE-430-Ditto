package com.ditto.cookiez.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zhihao Liang
 * @date 2020/9/9 22:08
 * @email s3798366@student.rmit.edu.au
 */

class UserServiceImplTest {
    @Test
    public void test(){
        UserServiceImpl service=new UserServiceImpl();
        System.out.println(service.getById(1).toString());

    }
}