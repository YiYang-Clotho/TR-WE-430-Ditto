package com.ditto.cookiez.controller;

import com.ditto.cookiez.entity.vo.RecipeResultVo;
import com.ditto.cookiez.service.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhihao Liang
 * @date 2020/10/5 21:21
 * @email s3798366@student.rmit.edu.au
 */
@RestController
public class IndexController {
    @Autowired
    IRecipeService recipeService;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        List<RecipeResultVo> voList = new ArrayList<>();
        int[] list2 = {36, 37, 38, 40, 48, 49, 50, 51};

        for (int i : list2
        ) {
            voList.add(recipeService.getResultVoById(i));
        }

        mv.addObject("voList", voList);
        return mv;
    }
}
