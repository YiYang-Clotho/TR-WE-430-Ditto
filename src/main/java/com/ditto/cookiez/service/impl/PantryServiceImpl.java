package com.ditto.cookiez.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ditto.cookiez.entity.Pantry;
import com.ditto.cookiez.mapper.PantryMapper;
import com.ditto.cookiez.service.IIngredientService;
import com.ditto.cookiez.service.IPantryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ditto.cookiez.service.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * Service Class
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
@Service
public class PantryServiceImpl extends ServiceImpl<PantryMapper, Pantry> implements IPantryService {
    @Autowired
    IIngredientService ingredientService;
    @Autowired
    IRecipeService recipeService;

    @Override
    public boolean savePantry(JSONObject param) {
        List<String> list = JSONObject.parseArray(param.toJSONString(), String.class);
//        int userId = param.getInteger("userId");
//        if (list != null) {
//            for (String str : list
//            ) {
//                int idOr1 =ingredientService.existedReturnId(str);
//               if(idOr1==-1){
////                   ingredientService.save();
//               }
//            }
//        }

        recipeService.searchByIngredients(list);
        return false;
    }
}
