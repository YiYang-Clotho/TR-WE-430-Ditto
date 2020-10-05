package com.ditto.cookiez.service.impl;

import com.ditto.cookiez.entity.Comment;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zhihao Liang
 * @date 2020/9/9 22:06
 * @email s3798366@student.rmit.edu.au
 */
class CommentServiceImplTest {
    public void test(){
        CommentServiceImpl service=new CommentServiceImpl();
        System.out.println(service.getById(1));
    }
}