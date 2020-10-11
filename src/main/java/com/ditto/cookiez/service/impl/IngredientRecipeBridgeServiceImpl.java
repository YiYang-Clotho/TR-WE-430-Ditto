package com.ditto.cookiez.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ditto.cookiez.entity.*;
import com.ditto.cookiez.mapper.IngredientRecipeBridgeMapper;
import com.ditto.cookiez.service.IIngredientRecipeBridgeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ditto.cookiez.service.IIngredientService;
import com.ditto.cookiez.service.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  Service Class
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-29
 */
@Service
public class IngredientRecipeBridgeServiceImpl extends ServiceImpl<IngredientRecipeBridgeMapper, IngredientRecipeBridge> implements IIngredientRecipeBridgeService {

    @Autowired
    IIngredientService ingredientService;
    @Autowired
    IRecipeService recipeService;

    @Override
    public List<Ingredient> getIngredientsByRecipeId(Integer recipeId) {
        QueryWrapper<IngredientRecipeBridge> qw = new QueryWrapper();
        if (recipeId != null) {
            qw.eq("recipe_id", recipeId);
        }
        List<IngredientRecipeBridge> bridges = list(qw);
        System.out.println("IngredientRecipeBridge");
        System.out.println(bridges);
        List<Integer> ids = new ArrayList<>();
        for (IngredientRecipeBridge bridge : bridges
        ) {
            ids.add(bridge.getIngredientId());
        }
        if(ids.size()!=0){
            return ingredientService.listByIds(ids);
        }else {
           return new  ArrayList<Ingredient>();
        }

    }

    @Override
    public List<Recipe> getRecipesByIngredientId(Integer ingredientId) {
        QueryWrapper<IngredientRecipeBridge> qw = new QueryWrapper();
        if (ingredientId != null) {
            qw.eq("ingredient_id", ingredientId);
        }
        List<IngredientRecipeBridge> bridges = list(qw);
        List<Integer> ids = new ArrayList<>();
        for (IngredientRecipeBridge bridge : bridges
        ) {
            ids.add(bridge.getRecipeId());
        }
        if(ids.size()!=0){
            return recipeService.listByIds(ids);
        }else {
            return new ArrayList<Recipe>();
        }

    }

    @Override
    public Boolean deleteByRecipeId(Integer recipeId) {
        QueryWrapper<IngredientRecipeBridge> qw=new QueryWrapper<>();
        qw.eq("recipe_id",recipeId);
        return  remove(qw);

    }
}
