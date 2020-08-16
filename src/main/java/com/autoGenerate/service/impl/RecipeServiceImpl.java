package com.autoGenerate.service.impl;

import com.autoGenerate.model.entity.Recipe;
import com.autoGenerate.mapper.RecipeMapper;
import com.autoGenerate.service.IRecipeService;
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
