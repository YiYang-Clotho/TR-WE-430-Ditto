package com.ditto.cookiez.service.impl;

import com.ditto.cookiez.entity.Recipe;
import com.ditto.cookiez.mapper.RecipeMapper;
import com.ditto.cookiez.service.IRecipeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
@Service
public class RecipeServiceImpl extends ServiceImpl<RecipeMapper, Recipe> implements IRecipeService {

}
