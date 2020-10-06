package com.ditto.cookiez.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ditto.cookiez.entity.Recipe;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
public interface RecipeMapper extends BaseMapper<Recipe> {

    List<Recipe> selectList(QueryWrapper<List> recipeList);
}
