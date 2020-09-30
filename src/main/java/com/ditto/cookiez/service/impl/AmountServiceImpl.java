package com.ditto.cookiez.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ditto.cookiez.entity.Amount;
import com.ditto.cookiez.mapper.AmountMapper;
import com.ditto.cookiez.service.IAmountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Service Class
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-29
 */
@Service
public class AmountServiceImpl extends ServiceImpl<AmountMapper, Amount> implements IAmountService {

    @Override
    public Amount getByRecipeIngredientId(Integer recipeId, Integer ingredientId) {

        QueryWrapper<Amount> qw = new QueryWrapper<>();
        qw.eq("recipe_id", recipeId);
        qw.eq("ingredient_id", ingredientId);
        return getOne(qw);

    }
}
