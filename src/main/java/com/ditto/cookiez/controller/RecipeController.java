package com.ditto.cookiez.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ditto.cookiez.entity.Recipe;
import com.ditto.cookiez.entity.Step;
import com.ditto.cookiez.entity.User;
import com.ditto.cookiez.entity.dto.RecipeDTO;
import com.ditto.cookiez.service.IRecipeService;
import com.ditto.cookiez.service.IStepService;
import com.ditto.cookiez.service.IUserService;
import com.ditto.cookiez.utils.ModelUtil;
import com.ditto.cookiez.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
@Slf4j
@RestController
public class RecipeController {
    @Autowired
    IRecipeService service;
    @Autowired
    IUserService userService;

    @Autowired
    IStepService stepService;

    @GetMapping("/recipe/add")
    public ModelAndView addRecipePage(@CookieValue(value = "accessToken") String accessToken) {
        ModelAndView mv = new ModelAndView("recipe/add");
        User user = userService.getUserByToken(accessToken);
        mv.addObject("user", user);
        return mv;
    }

    @GetMapping("/recipe/{recipeId}")
    public ModelAndView recipePage(@PathVariable Integer recipeId) {
        ModelAndView mv = new ModelAndView("recipe/show");
        RecipeDTO recipe = service.getRecipe(recipeId);
        if (recipe != null) {
            mv.addObject("recipeDTO", recipe);
        } else {
            log.info("Recipe is null, id is " + recipeId);
        }
        return mv;
    }

    @GetMapping("/recipe/{recipeId}/edit")
    public ModelAndView recipeEditPage(@PathVariable Integer recipeId,@CookieValue(value = "accessToken") String accessToken) {
        ModelAndView mv = new ModelAndView("recipe/edit");
        RecipeDTO recipe = service.getRecipe(recipeId);
        if (recipe != null) {
            User user = userService.getUserByToken(accessToken);
            mv.addObject("user", user);
            mv.addObject("recipeDTO", recipe);
        } else {
            log.info("Recipe is null, id is " + recipeId);
        }
        return mv;
    }


    @PutMapping("/api/recipe")
    public Integer updateRecipe(HttpServletRequest request, @RequestParam("data") String str) throws IOException {
       log.info(str);
        JSONObject jsonObject = JSONObject.parseObject(str);
        Map<String, MultipartFile> fileMap = ((MultipartHttpServletRequest) request).getFileMap();
        service.updateRecipe(jsonObject, fileMap);
        log.info(JSONObject.parseObject(str).toJSONString());

//        service.updateRecipe(json);
//        int id = recipe.getRecipeId();
//
//        for (Step s : stepArray
//        ) {
//            s.setRecipeId(id);
//            stepService.save(s);
//        }
//        log.info(json.toJSONString());
        return 1;
    }

    @DeleteMapping("/api/recipe")
    public ResponseEntity<JSONObject> deleteRecipe(@RequestParam Integer recipeId) {

        if (service.removeById(recipeId)) {
            return Response.ok("Delete Successfully");
        } else {
            log.info("Delete recipe failed. Id is " + recipeId);
        }
        return Response.bad("Delete Failed");
    }


    @PostMapping("/api/recipe")
    public ResponseEntity<JSONObject> addRecipe(HttpServletRequest request, @RequestParam("data") String str) throws IOException {
        JSONObject jsonObject = JSONObject.parseObject(str);
        Map<String, MultipartFile> fileMap = ((MultipartHttpServletRequest) request).getFileMap();
        Recipe recipe = service.addRecipe(jsonObject, fileMap);
        log.info(JSONObject.parseObject(str).toJSONString());

        for (MultipartFile v : fileMap.values()) {
            System.out.println(v.getName());
            System.out.println(v.getOriginalFilename());
        }


        return Response.ok("Succeed to add recipe!", recipe);
    }
}
