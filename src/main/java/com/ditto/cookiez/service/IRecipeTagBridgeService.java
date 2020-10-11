package com.ditto.cookiez.service;

import com.ditto.cookiez.entity.Recipe;
import com.ditto.cookiez.entity.RecipeTagBridge;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ditto.cookiez.entity.Tag;

import java.util.List;
import java.util.Set;

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

      Boolean deleteByRecipeId(Integer id);

      List<Recipe> getRecipesByTagId(Integer tagId);

      Set<Integer> getRecipesIdByTagId(Integer tagId);
}
