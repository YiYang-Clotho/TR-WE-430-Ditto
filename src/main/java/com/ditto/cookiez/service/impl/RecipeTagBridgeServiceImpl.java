package com.ditto.cookiez.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ditto.cookiez.entity.Recipe;
import com.ditto.cookiez.entity.RecipeTagBridge;
import com.ditto.cookiez.entity.Tag;
import com.ditto.cookiez.mapper.RecipeTagBridgeMapper;
import com.ditto.cookiez.service.IRecipeService;
import com.ditto.cookiez.service.IRecipeTagBridgeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ditto.cookiez.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Service Class
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-29
 */
@Service
public class RecipeTagBridgeServiceImpl extends ServiceImpl<RecipeTagBridgeMapper, RecipeTagBridge> implements IRecipeTagBridgeService {
    @Autowired
    ITagService tagService;
    @Autowired
    IRecipeService recipeService;

    @Override
    public List<Tag> getTagsByRecipeId(Integer recipeId) {
        QueryWrapper<RecipeTagBridge> qw = new QueryWrapper();
        if (recipeId != null) {
            qw.eq("recipe_id", recipeId);
        }
        List<RecipeTagBridge> bridges = list(qw);
        List<Integer> ids = new ArrayList<>();
        for (RecipeTagBridge bridge : bridges
        ) {
            ids.add(bridge.getTagId());
        }
        return tagService.listByIds(ids);
    }

    @Override
    public List<Recipe> getRecipesByTagId(Integer tagId) {
        QueryWrapper<RecipeTagBridge> qw = new QueryWrapper();
        if (tagId != null) {
            qw.eq("tag_id", tagId);
        }
        List<RecipeTagBridge> bridges = list(qw);
        List<Integer> ids = new ArrayList<>();
        for (RecipeTagBridge bridge : bridges
        ) {
            ids.add(bridge.getRecipeId());
        }
        return recipeService.listByIds(ids);
    }


}
