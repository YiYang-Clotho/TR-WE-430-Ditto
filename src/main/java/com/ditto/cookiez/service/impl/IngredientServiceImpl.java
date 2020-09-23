package com.ditto.cookiez.service.impl;

import com.ditto.cookiez.entity.Ingredient;
import com.ditto.cookiez.mapper.IngredientMapper;
import com.ditto.cookiez.service.IIngredientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-16
 */
@Service
public class IngredientServiceImpl extends ServiceImpl<IngredientMapper, Ingredient> implements IIngredientService {

}
