package com.ditto.cookiez.controller;


import com.alibaba.fastjson.JSONObject;
import com.ditto.cookiez.entity.vo.RecipeResultVo;
import com.ditto.cookiez.service.IRecipeService;
import com.ditto.cookiez.service.ITagService;
import com.ditto.cookiez.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *
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
