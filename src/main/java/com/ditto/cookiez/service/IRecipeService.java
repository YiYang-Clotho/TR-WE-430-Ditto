package com.ditto.cookiez.service;

import com.alibaba.fastjson.JSONObject;
import com.ditto.cookiez.entity.Recipe;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ditto.cookiez.entity.dto.RecipeDTO;

/**
 * <p>
 *  service class
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
public interface IRecipeService extends IService<Recipe> {
    public void updateRecipe(JSONObject json);
    public Recipe addRecipe(JSONObject json);

    RecipeDTO getRecipe(int id);
}
