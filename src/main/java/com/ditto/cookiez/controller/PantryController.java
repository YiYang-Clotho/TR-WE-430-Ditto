package com.ditto.cookiez.controller;


import org.springframework.web.bind.annotation.GetMapping;
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
//@RequestMapping("//pantry")
public class PantryController {
    @GetMapping("/pantry")
    public ModelAndView addRecipePage() {
        ModelAndView mv = new ModelAndView("/pantry/show");
        return mv;
    }

}
