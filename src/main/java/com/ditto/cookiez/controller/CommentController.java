package com.ditto.cookiez.controller;


import com.ditto.cookiez.service.impl.CommentServiceImpl;
import com.ditto.cookiez.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
@RestController
public class CommentController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CommentServiceImpl service;
    @RequestMapping(value="/hello", method= RequestMethod.GET)
    public String index() {

        String sql = "SELECT comment_content FROM comment WHERE comment_id = ?";

        // 通过jdbcTemplate查询数据库
        String mobile = service.getById(1).toString();

        return "Hello " + mobile;
    }
}
