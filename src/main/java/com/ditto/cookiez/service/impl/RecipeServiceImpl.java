package com.ditto.cookiez.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ditto.cookiez.entity.*;
import com.ditto.cookiez.entity.dto.IngredientDTO;
import com.ditto.cookiez.entity.dto.RecipeDTO;
import com.ditto.cookiez.entity.dto.StepDTO;
import com.ditto.cookiez.entity.vo.RecipeResultVo;
import com.ditto.cookiez.mapper.RecipeMapper;
import com.ditto.cookiez.service.*;
import com.ditto.cookiez.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * <p>
 * Service Class
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
    @Autowired
    IIngredientService ingredientService;
    @Autowired
    IAmountService amountService;
    @Autowired
    IIngredientRecipeBridgeService ingredientRecipeBridgeService;
    @Autowired
    ITagService tagService;
    @Autowired
    IRecipeTagBridgeService recipeTagBridgeService;

    //         TODO update ingredients
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
    }

    @Transactional
    @Override
    public Recipe addRecipe(JSONObject json, Map<String, MultipartFile> fileMap) throws IOException {
//        BUG: when upload tow same pictures, one picture will lost.
        Recipe recipe = json.getObject("recipe", Recipe.class);
        save(recipe);
        Integer recipeId = recipe.getRecipeId();
//        get data from json
        List<Tag> tags = json.getJSONArray("tags").toJavaList(Tag.class);
        List<Step> steps = json.getJSONArray("steps").toJavaList(Step.class);
        List<IngredientDTO> ingredients = json.getJSONArray("ingredients").toJavaList(IngredientDTO.class);
//       some magic value
        String coverKey = FileUtil.COVER;
        String stepImgPrefix = FileUtil.STEP_PREFIX;
//        save cover img
        if (fileMap.containsKey(coverKey)) {
            MultipartFile file = fileMap.get(coverKey);
            String url = FileUtil.uploadCoverToAws(file, recipeId);
//
            Img img = new Img(url);
            imgService.save(img);
            recipe.setRecipeCoverId(img.getImgId());
        }
//        save ingredients and its amount
        for (IngredientDTO ingredientDTO : ingredients
        ) {
            String ingredientName = ingredientDTO.getIngredientName();
            String amountContent = ingredientDTO.getAmount();
            Amount amount = new Amount(amountContent);

            Integer idOr1 = ingredientService.existedReturnId(ingredientName);
            if (idOr1 == -1) {
                Ingredient ingredient = new Ingredient(ingredientName);
                ingredientService.save(ingredient);
                idOr1 = ingredient.getIngredientId();
            }
            amount.setIngredientId(idOr1);
            amount.setRecipeId(recipeId);
            log.info(amount.toString());
            amountService.save(amount);
            IngredientRecipeBridge bridge = new IngredientRecipeBridge(idOr1, recipeId);
            ingredientRecipeBridgeService.save(bridge);
        }
//         save tag
        for (Tag tag : tags
        ) {
            int idOrN1 = tagService.existedReturnId(tag.getTagName());
            if (idOrN1 == -1) {
                tagService.save(tag);
            } else {
                tag.setTagId(idOrN1);
            }
            recipeTagBridgeService.save(new RecipeTagBridge(recipeId, tag.getTagId()));
        }
//         save steps
        for (Step step : steps
        ) {
            int order = step.getStepOrder();
            step.setRecipeId(recipeId);
            String nameInFileMap = stepImgPrefix + order;
            if (fileMap.containsKey(nameInFileMap)) {
                stepService.addStep(step, fileMap.get(nameInFileMap));
            } else {
                stepService.addStep(step);
            }

        }

        recipe.updateById();
        return recipe;
    }


    @Override
    public RecipeDTO getRecipe(int id) {
        Recipe recipe = getById(id);
//        get title and description
        RecipeDTO recipeDTO = new RecipeDTO(recipe);
//        get cover path
        if (recipe.getRecipeCoverId() != null) {
            recipeDTO.setCoverPath(imgService.getPathById(recipe.getRecipeCoverId()));
        }

//  TODO add author info

//        User user = userService.getById(recipe.getRecipeAuthorId());
//        recipeDTO.setAuthor(user.getUsername());

//        Get Step
        List<Step> stepList = stepService.list(new QueryWrapper<Step>().eq("recipe_id", id));
        List<StepDTO> stepDTOList = new ArrayList<>();
        for (Step step : stepList
        ) {
            StepDTO stepDTO = new StepDTO(step);
            stepDTO.setImgPath(imgService.getPathById(step.getImgId()));
            stepDTOList.add(stepDTO);
        }
        recipeDTO.setStepDTOList(stepDTOList);
//        Get tags
        List<Tag> tags = recipeTagBridgeService.getTagsByRecipeId(id);
        recipeDTO.setTagList(tags);
//      Get ingredients
        List<IngredientDTO> ingredientDTOS = new ArrayList<>();
        List<Ingredient> ingredients = ingredientRecipeBridgeService.getIngredientsByRecipeId(id);
        log.info(ingredients.toString());
        for (Ingredient i : ingredients
        ) {
            Amount amount = amountService.getByRecipeIngredientId(id, i.getIngredientId());
            log.info(amount.toString());
            ingredientDTOS.add(new IngredientDTO(i.getIngredientName(), amount.getAmountContent()));
        }
        log.info(ingredientDTOS.toString());
        recipeDTO.setIngredientDTOList(ingredientDTOS);

        return recipeDTO;
    }

    public List<RecipeResultVo> search(String keyword) {
        List<Recipe> recipes;
        Set<Integer> recipeIdSet = new HashSet<>();
//        title
        QueryWrapper<Recipe> qw = new QueryWrapper<>();
        qw.like("recipe_name", keyword);
        recipes = list(qw);
        for (Recipe recipe : recipes
        ) {
            recipeIdSet.add(recipe.getRecipeId());
        }
//        tag
        Set<Integer> recipeIdSetFromTag = searchRecipeIdListByTag(keyword);
        if (recipeIdSetFromTag != null) {
            recipeIdSet.addAll(recipeIdSetFromTag);
        }
//        ingredient

        return null;
    }

    private   Set<Integer> searchRecipeIdListByTag(String keyword) {
        QueryWrapper<Tag> qw = new QueryWrapper<>();
        List<Recipe> recipes = new ArrayList<>();
        Set<Integer> recipeIdSet = new HashSet<>();
//        search tag using "like"
        qw.like("tag_name", keyword);
        List<Tag> tags = tagService.list(qw);
        List<Integer> tagIdList = new ArrayList<>();
        for (Tag tag : tags
        ) {
            tagIdList.add(tag.getTagId());
        }
        List<RecipeTagBridge> recipeTagBridges = recipeTagBridgeService.listByIds(tagIdList);
        for (RecipeTagBridge recipeTagBridge : recipeTagBridges
        ) {
            recipeIdSet.add(recipeTagBridge.getRecipeId());
        }
        return recipeIdSet;
    }
//    private Set<Integer> searchRecipeIdListByTag

}
