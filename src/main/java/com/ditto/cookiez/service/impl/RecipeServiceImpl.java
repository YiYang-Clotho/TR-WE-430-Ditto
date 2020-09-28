package com.ditto.cookiez.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ditto.cookiez.entity.Img;
import com.ditto.cookiez.entity.Recipe;
import com.ditto.cookiez.entity.Step;
import com.ditto.cookiez.entity.User;
import com.ditto.cookiez.entity.dto.RecipeDTO;
import com.ditto.cookiez.entity.dto.StepDTO;
import com.ditto.cookiez.mapper.RecipeMapper;
import com.ditto.cookiez.service.IImgService;
import com.ditto.cookiez.service.IRecipeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ditto.cookiez.service.IStepService;
import com.ditto.cookiez.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    IUserService userService;

//TODO update ingredients
    @Override
    public void updateRecipe(JSONObject json) {
        Recipe recipe = json.getObject("recipe", Recipe.class);
//        update recipe basic info
        recipe.updateById();
        List<StepDTO> stepDTOs = json.getJSONArray("steps").toJavaList(StepDTO.class);
//        update step
        for (StepDTO stepDTO : stepDTOs
        ) {
            Img img = new Img(stepDTO.getImgId(), stepDTO.getImgPath());
            img.updateById();
            Step step = new Step(stepDTO.getStepOrder(), stepDTO.getStepContent());
            step.setImgId(stepDTO.getImgId());
            step.updateById();
        }
//        update ingredients

//        for (:
//             ) {
//
//        }
//        update tags
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
        Recipe recipe = getById(id);
        RecipeDTO recipeDTO = new RecipeDTO(recipe);

        User user = userService.getById(recipe.getRecipeAuthorId());
        recipeDTO.setAuthor(user.getUsername());

        List<Step> stepList = stepService.list(new QueryWrapper<Step>().eq("recipe_id", recipe.getRecipeId()));
        List<StepDTO> stepDTOList = new ArrayList<>();
        for (Step step : stepList
        ) {
            StepDTO stepDTO = new StepDTO(step);
            Img img = imgService.getById(step.getImgId());
            stepDTO.setImgPath(img.getImgPath());
            stepDTOList.add(stepDTO);
        }
        recipeDTO.setStepDTOList(stepDTOList);


        return recipeDTO;
    }
}
