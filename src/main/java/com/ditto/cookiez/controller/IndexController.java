package com.ditto.cookiez.controller;

import com.ditto.cookiez.entity.vo.RecipeResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Zhihao Liang
 * @date 2020/10/5 21:21
 * @email s3798366@student.rmit.edu.au
 */
@RestController
public class IndexController {
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv=new ModelAndView("index/index");
        RecipeResultVo vo=new RecipeResultVo("Rice","Cooking a perfect batch of white rice without a rice cooker can be a challenge. That's why we are going for forget about cooking rice on the stove and show you the incredibly delicious and absolutely foolproof world of pilaf!","ZhihaoLiang");
        vo.setCoverPath("https://cookiez-img2.s3.amazonaws.com/images/recipes/32/step-1.png");
        vo.setUrl("/recipe/20");
        mv.addObject("vo",vo);
        return mv;
    }
}
