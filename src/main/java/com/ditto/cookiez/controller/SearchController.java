package com.ditto.cookiez.controller;

import com.ditto.cookiez.service.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Zhihao Liang
 * @date 2020/10/5 16:51
 * @email s3798366@student.rmit.edu.au
 */
public class SearchController {

    @Autowired
    IRecipeService recipeService;
    @GetMapping
    public void resultPage(@RequestParam String keyword){

    }
}
