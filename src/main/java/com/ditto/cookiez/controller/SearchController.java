package com.ditto.cookiez.controller;

import com.ditto.cookiez.entity.vo.RecipeResultVo;
import com.ditto.cookiez.service.IRecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value="/search",method= RequestMethod.GET)
    public ModelAndView resultPage(@RequestParam(defaultValue = "") String keyword) {

        List<RecipeResultVo> voList = recipeService.search(keyword);
        System.out.println(keyword);
//        ModelAndView mv = new ModelAndView("/recipe/result");
//        mv.addObject("recipeResultVo", voList);
//        return mv;
        return null;
    }
}
