package com.ditto.cookiez.controller;


import com.ditto.cookiez.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-16
 */
@RestController
@RequestMapping("//tags")
public class TagController {
    private final Logger logger = LoggerFactory.getLogger(TagController.class);
    @Autowired
    IUserService service;

    @GetMapping("/tags")
    public ModelAndView loginPage() {
        return new ModelAndView("/tag/tags");
    }
}
