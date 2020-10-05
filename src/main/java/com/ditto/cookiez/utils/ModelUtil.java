package com.ditto.cookiez.utils;

import com.ditto.cookiez.auth.JwtTokenUtil;
import com.ditto.cookiez.controller.UserController;
import com.ditto.cookiez.entity.User;
import com.ditto.cookiez.service.IUserService;
import com.ditto.cookiez.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhihao Liang
 * @date 2020/9/16 11:12
 * @email s3798366@student.rmit.edu.au
 */
public class ModelUtil {
    private final static Logger logger = LoggerFactory.getLogger(ModelUtil.class);


    public static Map<String, Object> getBaseModel(String token) {
        Map<String, Object> model = new HashMap<>();
        IUserService userService = new UserServiceImpl();
        System.out.println(token+"token");
        User user = userService.getUserByToken(token);
        logger.info(user.toString()+"30");
        model.put("user", user);
        return model;

    }
}
