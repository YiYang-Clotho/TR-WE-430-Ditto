package com.ditto.cookiez.service;

import com.ditto.cookiez.entity.Ingredient;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-16
 */
public interface IIngredientService extends IService<Ingredient> {

    List<Ingredient> getIngredientsByTagId(Integer tagId);
    Integer existedReturnId (String name);
}
