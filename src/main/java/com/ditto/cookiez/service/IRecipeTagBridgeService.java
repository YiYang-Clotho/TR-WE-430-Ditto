package com.ditto.cookiez.service;

import com.ditto.cookiez.entity.Recipe;
import com.ditto.cookiez.entity.RecipeTagBridge;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ditto.cookiez.entity.Tag;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-29
 */
public interface IRecipeTagBridgeService extends IService<RecipeTagBridge> {
      List<Tag> getTagsByRecipeId(Integer recipeId);

      List<Recipe> getRecipesByTagId(Integer tagId);
}
