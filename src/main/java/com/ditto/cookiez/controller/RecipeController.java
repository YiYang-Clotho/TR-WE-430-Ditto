package com.ditto.cookiez.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ditto.cookiez.entity.Recipe;
import com.ditto.cookiez.entity.Step;
import com.ditto.cookiez.service.IRecipeService;
import com.ditto.cookiez.service.IStepService;
import com.ditto.cookiez.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 * 前端控制器
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
    IStepService stepService;

    @GetMapping("/recipe/{recipeId}")
    public ModelAndView recipePage(@PathVariable Integer recipeId) {
        ModelAndView mv = new ModelAndView("recipe/detail");
        Recipe recipe = service.getById(recipeId);
        if (recipe != null) {
            mv.addObject(recipe);
        } else {
            log.info("Recipe is null, id is " + recipeId);
        }
        return mv;
    }

    @PutMapping("/api/recipe")
    public Integer updateRecipe(@RequestBody JSONObject json) {
        Recipe recipe = json.getObject("recipe", Recipe.class);

        List<Step> stepArray = JSONObject.parseArray(json.getJSONArray("steps").toString(), Step.class);
        service.update();
        int id = recipe.getRecipeId();

        for (Step s:stepArray
             ) {
            s.setRecipeId(id);
            stepService.save(s);
        }
        log.info(json.toJSONString());
        return 1;
    }

    @DeleteMapping("/api/recipe")
    public ResponseEntity<JSONObject> deleteRecipe(@RequestParam Integer id) {
        if (service.removeById(id)) {
            return Response.ok("Delete Successfully");
        } else {
            log.info("Delete recipe failed. Id is " + id);
        }
        return Response.bad("Delete Failed");
    }

    @GetMapping("/recipe/add")
    public ModelAndView addRecipePage() {
        ModelAndView mv = new ModelAndView("recipe/add");
        return mv;
    }


}
