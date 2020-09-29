package com.ditto.cookiez.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ditto.cookiez.entity.Img;
import com.ditto.cookiez.entity.Recipe;
import com.ditto.cookiez.entity.Step;
import com.ditto.cookiez.entity.dto.RecipeDTO;
import com.ditto.cookiez.entity.dto.StepDTO;
import com.ditto.cookiez.mapper.RecipeMapper;
import com.ditto.cookiez.service.IImgService;
import com.ditto.cookiez.service.IRecipeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ditto.cookiez.service.IStepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
@Slf4j
@Service
public class RecipeServiceImpl extends ServiceImpl<RecipeMapper, Recipe> implements IRecipeService {
    @Autowired
    IImgService imgService;
    @Autowired
    IStepService stepService;

    @Override
    public void updateRecipe(JSONObject json) {
        Recipe recipe = json.getObject("recipe", Recipe.class);
        recipe.updateById();
        List<StepDTO> stepDTOs = json.getJSONArray("steps").toJavaList(StepDTO.class);
        for (StepDTO stepDTO : stepDTOs
        ) {
            Img img = new Img(stepDTO.getImgId(), stepDTO.getImgPath());
            img.updateById();
            Step step = new Step(stepDTO.getStepOrder(), stepDTO.getStepContent());
            step.setImgId(stepDTO.getImgId());
            step.updateById();
        }
    }

    @Override
    public Recipe addRecipe(JSONObject json) {
        Recipe recipe = json.getObject("recipe", Recipe.class);
        List<StepDTO> stepDTOs = json.getJSONArray("steps").toJavaList(StepDTO.class);
        save(recipe);
        int recipeId = recipe.getRecipeId();
        for (StepDTO stepDTO : stepDTOs
        ) {
            log.info(stepDTO.toString());
            Img img = new Img();
            if (stepDTO.getImgPath() != null) {
                img.setImgPath(stepDTO.getImgPath());
                imgService.save(img);
            }
            Step step = new Step(stepDTO.getStepOrder(), stepDTO.getStepContent());
            step.setRecipeId(recipeId);
            if (img.getImgId() != null) {
                step.setImgId(img.getImgId());
            }
            stepService.save(step);
        }
        return recipe;
    }

    @Override
    public RecipeDTO getRecipe(int id) {
        return null;
    }
}
