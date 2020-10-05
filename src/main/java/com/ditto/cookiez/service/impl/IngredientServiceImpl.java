package com.ditto.cookiez.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ditto.cookiez.entity.Ingredient;
import com.ditto.cookiez.entity.IngredientTagBridge;
import com.ditto.cookiez.mapper.IngredientMapper;
import com.ditto.cookiez.service.IIngredientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ditto.cookiez.service.IIngredientTagBridgeService;
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
 * @since 2020-09-16
 */
@Service
public class IngredientServiceImpl extends ServiceImpl<IngredientMapper, Ingredient> implements IIngredientService {
    @Autowired
    ITagService tagService;
    @Autowired
    IIngredientTagBridgeService ingredientTagBridgeService;
    private QueryWrapper<Ingredient> qw = new QueryWrapper<>();


    @Override
    public List<Ingredient> getIngredientsByTagId(Integer tagId) {


//        List<Integer> idList = new ArrayList<Integer>();
//        List<IngredientTagBridge> ingredientTagBridgeList = ingredientTagBridgeService.list(new QueryWrapper<IngredientTagBridge>().eq("tag_id", tagId));
//        idList.add(tagId);
//        if (tagId != null) {
//            return listByIds(idList);
//        }
//        return null;
        return null;
    }

    @Override
    public Integer existedReturnId(String name) {
        qw.clear();
        Ingredient ingredient = getOne(qw.eq("ingredient_name", name));
        if (ingredient != null) {
            return ingredient.getIngredientId();
        } else {
            return -1;
        }

    }
}
