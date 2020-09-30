package com.ditto.cookiez.service;

import com.ditto.cookiez.entity.Ingredient;
import com.ditto.cookiez.entity.IngredientRecipeBridge;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ditto.cookiez.entity.Recipe;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-29
 */
public interface IIngredientRecipeBridgeService extends IService<IngredientRecipeBridge> {
    List<Ingredient> getIngredientsByRecipeId(Integer recipeId);
    List<Recipe> getRecipesByIngredientId(Integer ingredientId);
}
