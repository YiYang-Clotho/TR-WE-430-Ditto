package com.autoGenerate.controller;


import com.autoGenerate.model.entity.User;
import com.autoGenerate.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
@RestController
@RequestMapping("//user")
public class UserController {
    @Autowired
    IUserService service;
    public String login() {
        User user=
    }
}
