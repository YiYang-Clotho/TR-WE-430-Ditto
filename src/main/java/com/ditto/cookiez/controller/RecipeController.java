package com.ditto.cookiez.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
@RestController
@RequestMapping("/recipe")
public class RecipeController {
    @GetMapping("{recipeId}")
    public String recipe() {
        return "";
    }
    @GetMapping("/add")
    public ModelAndView addRecipePage() {

        return new ModelAndView("recipe/add");
    }
}
