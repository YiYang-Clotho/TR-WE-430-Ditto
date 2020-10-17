package com.ditto.cookiez.controller;


import com.alibaba.fastjson.JSONObject;
import com.ditto.cookiez.entity.Ingredient;
import com.ditto.cookiez.entity.vo.RecipeResultVo;
import com.ditto.cookiez.service.IPantryService;
import com.ditto.cookiez.service.IRecipeService;
import com.ditto.cookiez.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
@RestController
//@RequestMapping("//pantry")
public class PantryController {
    @Autowired
    IPantryService service;
    @Autowired
    IRecipeService recipeService;

    @GetMapping("/pantry")
    public ModelAndView addRecipePage() {
        ModelAndView mv = new ModelAndView("/pantry/show");
        return mv;
    }

    @PostMapping("/api/pantry")
    public ResponseEntity<JSONObject> savePantry(@RequestBody JSONObject param) {
        List<String> list = JSONObject.parseArray(param.getJSONArray("keywords").toString(), String.class);
        List<RecipeResultVo> voList = recipeService.searchByIngredients(list);
        return Response.ok("search successfully", voList);

    }

}
