package com.ditto.cookiez.utils;

import com.ditto.cookiez.auth.JwtTokenUtil;
import com.ditto.cookiez.controller.UserController;
import com.ditto.cookiez.entity.User;
import com.ditto.cookiez.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


    public static Map<String, Object> getBaseModel() {
//        Map<String, Object> model = new HashMap<>();
//        UserServiceImpl userService = new UserServiceImpl();
//        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
//        if("".equals(token)){
//            return model;
//        }
//       token= token.substring(7);
//        logger.info(token);
//        String username = jwtTokenUtil.getUsernameFromToken(token);
//        if (username != null) {
//            User user = userService.getByUsername(username);
//            model.put("user", user);
//            logger.info(user.toString());
//        }
//        return model;
        return null;
    }
}
