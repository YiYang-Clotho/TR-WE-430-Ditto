package com.ditto.cookiez.controller;


import com.alibaba.fastjson.JSONObject;
import com.ditto.cookiez.entity.Recipe;
import com.ditto.cookiez.entity.Tag;
import com.ditto.cookiez.entity.User;
import com.ditto.cookiez.entity.dto.RecipeDTO;
import com.ditto.cookiez.service.IRecipeService;
import com.ditto.cookiez.service.IStepService;
import com.ditto.cookiez.service.ITagService;
import com.ditto.cookiez.service.IUserService;
import com.ditto.cookiez.utils.Response;
import com.ditto.cookiez.utils.WebUtil;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    ITagService service;

    @Autowired
    IRecipeService recipeService;

    @GetMapping("/tags")
    public ModelAndView tagPage() {
        return new ModelAndView("/tag/show");
    }

    @PostMapping("/tags")
    public void searchByTag(@RequestBody JSONObject param, HttpServletResponse response) {
        String tag_name = param.getString("tag_name");
        int tag_id = service.existedReturnId(tag_name);
        List<Recipe> recipeList = recipeService.getRecipesByTagId(tag_id);
        Map<String, Object> models = new HashMap<>();
        if (recipeList != null) {
            models.put("recipes", recipeList);
        } else {
//            return Response.bad("Failed to login");
        }
    }

    @GetMapping("/tags/{tag_name}")
    public ModelAndView searchPage(@RequestParam String tag_name) {
        ModelAndView mv = new ModelAndView("tag/show");
        int tag_id = service.existedReturnId(tag_name);
        List<Recipe> recipeList = recipeService.getRecipesByTagId(tag_id);
        if (recipeList != null) {
            mv.addObject("recipes",recipeList);
        } else {
            // log.info("Sorry, cannot find any recipe by the tag");
            new ModelAndView("/tag/show");
        }
        return mv;
    }

}
