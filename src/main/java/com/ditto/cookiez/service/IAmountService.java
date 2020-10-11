package com.ditto.cookiez.service;

import com.ditto.cookiez.entity.Amount;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-29
 */
public interface IAmountService extends IService<Amount> {
    Amount getByRecipeIngredientId(Integer recipeId, Integer ingredientId);
    Boolean deleteByRecipeId(Integer recipeId);
}
