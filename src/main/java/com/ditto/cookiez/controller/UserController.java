package com.ditto.cookiez.controller;

import com.alibaba.fastjson.JSONObject;
import com.ditto.cookiez.service.IImgService;
import com.ditto.cookiez.utils.WebUtil;
import com.ditto.cookiez.entity.User;
import com.ditto.cookiez.service.IUserService;
import com.ditto.cookiez.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
/*
*
*  @DeleteMapping("/api/login")
    public void login(@RequestParam String name) {
        System.out.println(name.toString());

    }
      function login(obj) {
        axios.delete("/api/login", {
            params: {
                name:"123"
            }
        })
    }
----------------------------------------
*   function login(obj) {
        axios.get("/api/login", {
            params: {
                name:"123"
            }
        })
    }
    *   @GetMapping("/api/login")
    public String getLogin(@RequestParam String name) {
        System.out.println(name);
        return "";
    }
-----------------------------------
*    function login(obj) {
        axios.post("/api/login", {
            name:"123"
    })
    }

*    @PostMapping("/api/login")
    public void login(@RequestBody JSONObject param ) {
param.getStirng

    }
    * ------------------------------------
    *     function login(obj) {
        axios.put("/api/login", {
            name:"123"
    })
    } @PutMapping("/api/login")
    public void login(@RequestBody JSONObject name) {
        System.out.println(name.toString());
    }
* */
@Slf4j
@RestController
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    IUserService service;
    @Autowired
    IImgService imgService;

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("/user/login");
    }

    @PostMapping("/api/login")
    public void login(@RequestBody JSONObject param, HttpServletResponse response) {
        String username = param.getString("username");
        String password = param.getString("password");
        User user = service.auth(username, password);
        Map<String, Object> models = new HashMap<>();
        models.put("user", user);
        logger.info(user.getAccessToken());
        WebUtil.setCookieVal(response, "accessToken", user.getAccessToken(), 3600);

        if (user != null) {
//            return Response.ok(ResponseMsg.SUCCEED_TO_LOGIN.v(), user);
        } else {
//            return Response.bad("Failed to login");
        }
    }

    @GetMapping("/register")
    public ModelAndView registerPage() {
        return new ModelAndView("user/register");
    }

    @PostMapping("/api/register")
    public ResponseEntity<JSONObject> register(@RequestBody JSONObject param) {
        String username = param.getString("username"), password = param.getString("password");

        User user = new User(username, password);

        if (service.save(user)) {
            return Response.ok("Succeed to register");
        }
        return Response.bad("Failed to register");
    }


    @GetMapping("/user/kitchen")
    public ModelAndView kitchenPage(@CookieValue(value = "accessToken") String accessToken) {
      ModelAndView mv=  new ModelAndView("user/kitchen");
        User user = service.getUserByToken(accessToken);

        mv.addObject("user", user);
        return mv;
    }

    @GetMapping("/user/saves")
    public ModelAndView savesPage(@CookieValue(value = "accessToken") String accessToken) {

        ModelAndView mv=  new ModelAndView("user/saves");
        User user = service.getUserByToken(accessToken);
        mv.addObject("user", user);

        return mv;
    }


    @GetMapping("/user/profile")
    public ModelAndView profilePage(@CookieValue(value = "accessToken") String accessToken) {
        User user = service.getUserByToken(accessToken);
        ModelAndView mv = new ModelAndView("user/profile");
        mv.addObject("user", user);
        logger.info(user.toString());
//        mv.addAllObjects(ModelUtil.getBaseModel(session));
        return mv;
    }

    @PutMapping("/api/profile")
    public ResponseEntity<JSONObject> addRecipe(HttpServletRequest request, HttpServletResponse response, @RequestParam("data") String str) throws IOException {

        log.info(str);
        JSONObject jsonObject = JSONObject.parseObject(str);
        Map<String, MultipartFile> fileMap = ((MultipartHttpServletRequest) request).getFileMap();
        User user = service.updateProfile(jsonObject, fileMap);
        log.info("166");
        log.info(JSONObject.parseObject(str).toJSONString());
        WebUtil.setCookieVal(response, "accessToken", user.getAccessToken(), 3600);
        for (MultipartFile v : fileMap.values()) {
            System.out.println(v.getName());
            System.out.println(v.getOriginalFilename());
        }


        return Response.ok("Succeed to update your profile");
    }


}
