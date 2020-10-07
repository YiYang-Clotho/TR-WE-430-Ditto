package com.ditto.cookiez.controller;


import com.alibaba.fastjson.JSONObject;
import com.ditto.cookiez.entity.Recipe;
import com.ditto.cookiez.entity.Tag;
import com.ditto.cookiez.entity.User;
import com.ditto.cookiez.entity.dto.RecipeDTO;
import com.ditto.cookiez.entity.vo.RecipeResultVo;
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
import java.util.Set;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-16
 */
@RestController
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
    public ResponseEntity<JSONObject> searchByTag(@RequestBody JSONObject param, HttpServletResponse response) {
        String tag_name = param.getString("tag_name");
        if (tag_name == null) {
            return Response.ok("Do not have to find");
        }else{
            List<RecipeResultVo> recipeList = recipeService.search(tag_name);
            System.out.println("Print recipeList" + recipeList.toString());
            if (recipeList != null) {
                return Response.ok("Succeed to find the recipes", recipeList);
            } else {
                return Response.bad("Failed to find the recipes");
            }
        }

    }

//    @PutMapping("/tags")
//    public ResponseEntity<JSONObject> searchPage(@RequestBody JSONObject param) {
//        String tag_name = param.getString("tag_name");
//        ModelAndView mv = new ModelAndView("tag/show");
//        if (tag_name == null) {
//            return Response.ok("Do not have to find");
//
//        }else{
//            List<RecipeResultVo> recipeList = recipeService.search(tag_name);
//            if (recipeList != null){
//                mv.addObject("recipes",recipeList);
//                return Response.ok("Succeed to find the recipes", recipeList);
//            }else {
//                new ModelAndView("/tag/show");
//                return Response.bad("Failed to find the recipes");
//            }
//        }
//    }

}
