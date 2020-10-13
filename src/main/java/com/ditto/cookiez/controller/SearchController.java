package com.ditto.cookiez.controller;

import com.alibaba.fastjson.JSONObject;
import com.ditto.cookiez.entity.vo.RecipeResultVo;
import com.ditto.cookiez.service.IRecipeService;
import com.ditto.cookiez.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Zhihao Liang
 * @date 2020/10/5 16:51
 * @email s3798366@student.rmit.edu.au
 */
@Slf4j
@RestController
public class SearchController {

    @Autowired
    IRecipeService recipeService;



    @GetMapping("/search")
    public ModelAndView searchPage() {
        return new ModelAndView("/search/search");
    }

    @PostMapping("/api/search/tags")
    public ResponseEntity<JSONObject> searchByTag(@RequestBody JSONObject param) {
        List<String> list = JSONObject.parseArray(param.getJSONArray("keywords").toString(), String.class);
        List<RecipeResultVo> recipeList = recipeService.searchByTags(list);
        return Response.ok("Succeed to find the recipes", recipeList);
    }

    @PostMapping("/api/search/ingredients")
    public ResponseEntity<JSONObject> searchByIngredients(@RequestBody JSONObject param) {
        List<String> list = JSONObject.parseArray(param.getJSONArray("keywords").toString(), String.class);
        List<RecipeResultVo> recipeList = recipeService.searchByIngredients(list);
        return Response.ok("Succeed to find the recipes", recipeList);

    }

    @PostMapping("/api/search/all")
    public ResponseEntity<JSONObject> search(@RequestBody JSONObject param) {
        List<String> list = JSONObject.parseArray(param.getJSONArray("keywords").toString(), String.class);
        List<RecipeResultVo> recipeList = recipeService.search(list);
        return Response.ok("Succeed to find the recipes", recipeList);

    }

    @PostMapping("/api/search/title")
    public ResponseEntity<JSONObject> searchByTitle(@RequestBody JSONObject param) {
        List<String> list = JSONObject.parseArray(param.getJSONArray("keywords").toString(), String.class);
        List<RecipeResultVo> recipeList = recipeService.searchByTitle(list);
        return Response.ok("Succeed to find the recipes", recipeList);

    }
}
