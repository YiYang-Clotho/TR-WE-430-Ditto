package com.ditto.cookiez.controller;
import com.ditto.cookiez.entity.User;
import com.ditto.cookiez.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

public class UserController {
    @Autowired
    IUserService service;

    @GetMapping("/login")
    public String login() {
        return "";
    }

    @GetMapping("/register")
    public String register() {
        return "";
    }

    @GetMapping("user/{userId}")
    public String profile() {
        return "";
    }

}
