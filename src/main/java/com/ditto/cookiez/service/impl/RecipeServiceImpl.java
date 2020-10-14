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
    public static String coverKey = FileUtil.COVER;
    public static String stepImgPrefix = FileUtil.STEP_PREFIX;

    //         TODO update ingredients
    @Transactional
    @Override
    public Boolean updateRecipe(JSONObject json, Map<String, MultipartFile> fileMap) throws IOException {
        Recipe recipe = json.getObject("recipe", Recipe.class);
        Integer recipeId = recipe.getRecipeId();
//        get data from json
        List<Tag> tags = json.getJSONArray("tags").toJavaList(Tag.class);
        List<IngredientDTO> ingredients = json.getJSONArray("ingredients").toJavaList(IngredientDTO.class);

        //       some magic value
        recipe.setRecipeCreatedTime(null);

        setUpCover(fileMap, recipe);
        //        update step
        List<StepDTO> stepDTOs = json.getJSONArray("steps").toJavaList(StepDTO.class);
        int index = 0;
        for (StepDTO stepDTO : stepDTOs
        ) {
            index++;
            Step step = stepService.getByOrderAndRecipeId(stepDTO.getStepOrder(), recipeId);
            if (step == null) {
                step = new Step();
                step.setRecipeId(recipeId);
                stepService.save(step);
            }
            step.setStepContent(stepDTO.getStepContent());
            step.setStepOrder(stepDTO.getStepOrder());


            Integer order = stepDTO.getStepOrder();
            if (fileMap.containsKey(stepImgPrefix + stepDTO.getStepOrder())) {
                String nameInFileMap = stepImgPrefix + order;
                log.info(FileUtil.getRecipeStepRelativePath(recipeId, order, FileUtil.getFileType(fileMap.get(nameInFileMap).getOriginalFilename())));
                FileUtil.delete(FileUtil.getRecipeStepRelativePath(recipeId, order, FileUtil.getFileType(fileMap.get(nameInFileMap).getOriginalFilename())));
                String url = FileUtil.uploadStepImgToAws(fileMap.get(nameInFileMap), recipeId, order);
                Img img = new Img(url);
                if (step.getImgId() != null) {
                    img.setImgId(step.getImgId());
                    img.updateById();
                } else {
                    imgService.save(img);
                }
                step.setImgId(img.getImgId());
            }
            step.updateById();
        }
//        delete redundant steps
        QueryWrapper<Step> stepQueryWrapper = new QueryWrapper<>();
        stepQueryWrapper.eq("recipe_id", recipeId);
        int count = stepService.count(stepQueryWrapper);
        for (int i = index + 1; i <= count; i++) {
            stepQueryWrapper.clear();
            stepQueryWrapper.eq("recipe_id", recipeId);
            stepQueryWrapper.eq("step_order", i);
            stepService.remove(stepQueryWrapper);
        }

//        delete previous ingredients and amount
        ingredientRecipeBridgeService.deleteByRecipeId(recipeId);
        amountService.deleteByRecipeId(recipeId);
//        update ingredient
        saveIngredientAndAmount(ingredients, recipeId);
//       delete tags
        recipeTagBridgeService.deleteByRecipeId(recipeId);
//        update tags
        saveTag(tags, recipeId);
        recipe.updateById();
        return true;
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
        saveIngredientAndAmount(ingredients, recipeId);
//         save tag
        saveTag(tags, recipeId);
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
        return null;
    }

    private void saveTag(List<Tag> tags, Integer recipeId) {

        for (Tag tag : tags
        ) {
            tagService.save(tag);
            recipeTagBridgeService.save(new RecipeTagBridge(recipeId, tag.getTagId()));
        }
    }


    private void setUpCover(Map<String, MultipartFile> fileMap, Recipe recipe) throws IOException {
        if (fileMap.containsKey(coverKey)) {
            MultipartFile file = fileMap.get(coverKey);
            String url = FileUtil.uploadCoverToAws(file, recipe.getRecipeId());
            Img img = new Img(url);
            imgService.save(img);
            recipe.setRecipeCoverId(img.getImgId());
        }
    }

    private void saveIngredientAndAmount(List<IngredientDTO> ingredients, Integer recipeId) {
        for (IngredientDTO ingredientDTO : ingredients
        ) {
            String ingredientName = ingredientDTO.getIngredientName();
            String amountContent = ingredientDTO.getAmount();
            Amount amount = new Amount(amountContent);
            Ingredient ingredient = new Ingredient(ingredientName);
            ingredientService.save(ingredient);
            amount.setIngredientId(ingredient.getIngredientId());
            amount.setRecipeId(recipeId);
            log.info(amount.toString());
            amountService.save(amount);
            IngredientRecipeBridge bridge = new IngredientRecipeBridge(ingredient.getIngredientId(), recipeId);
            ingredientRecipeBridgeService.save(bridge);
        }

    }

    //    private void setUpStepImg(Map<String, MultipartFile> fileMap, Step step) throws IOException {
//        if (fileMap.containsKey(coverKey)) {
//            MultipartFile file = fileMap.get(coverKey);
//            String url = FileUtil.uploadCoverToAws(file, recipe.getRecipeId());
////
//            Img img = new Img(url);
//            imgService.save(img);
//            recipe.setRecipeCoverId(img.getImgId());
//        }
//    }
    @Transactional
    @Override
    public RecipeDTO getRecipe(Integer id) {
        Recipe recipe = getById(id);
//        get title and description
        RecipeDTO recipeDTO = new RecipeDTO(recipe);
        recipeDTO.setId(id);
//        get cover path
        if (recipe.getRecipeCoverId() != null) {
            recipeDTO.setCoverPath(imgService.getPathById(recipe.getRecipeCoverId()));
        }

//  TODO add author info

        User user = userService.getById(recipe.getRecipeAuthorId());
        recipeDTO.setAuthor(user.getUsername());

//        Get Step
        List<Step> stepList = stepService.list(new QueryWrapper<Step>().eq("recipe_id", id));
        List<StepDTO> stepDTOList = new ArrayList<>();
        for (Step step : stepList
        ) {

            StepDTO stepDTO = new StepDTO(step);
            stepDTO.setImgPath(imgService.getPathById(step.getImgId()));
            stepDTOList.add(stepDTO);
            stepDTO.setId(step.getStepId());
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

    @Override
    public List<RecipeResultVo> search(List<String> keywords) {
        Set<Integer> recipeIdSet = new HashSet<>();
//        title
        for (String word : keywords
        ) {
            recipeIdSet.addAll(searchRecipeIdListByTitle(word));
            recipeIdSet.addAll(searchRecipeIdListByTag(word));
            recipeIdSet.addAll(searchRecipeIdListByIngredient(word));
        }

        return getResultVoListByIdList(recipeIdSet);


    }

    @Override
    public List<RecipeResultVo> searchByIngredients(List<String> strList) {
        Set<Integer> recipeIdSet = new HashSet<>();
        for (String ingred : strList
        ) {
            Set<Integer> idList = searchRecipeIdListByIngredient(ingred);
            recipeIdSet.addAll(idList);
        }
        return getResultVoListByIdList(recipeIdSet);

    }

    @Override
    public List<RecipeResultVo> searchByTitle(List<String> strList) {
        Set<Integer> recipeIdSet = new HashSet<>();

        for (String ingred : strList
        ) {
            Set<Integer> idList = searchRecipeIdListByTitle(ingred);
            recipeIdSet.addAll(idList);
        }
        return getResultVoListByIdList(recipeIdSet);
    }

    @Override
    public List<RecipeResultVo> searchByTags(List<String> keywords) {
        Set<Integer> recipeIdSet = new HashSet<>();
        for (String tag : keywords
        ) {
            Set<Integer> idList = searchRecipeIdListByTag(tag);
            recipeIdSet.addAll(idList);
        }

        return getResultVoListByIdList(recipeIdSet);

    }

    private Set<Integer> searchRecipeIdListByTag(String keyword) {
        QueryWrapper<Tag> qw = new QueryWrapper<>();
        Set<Integer> recipeIdSet = new HashSet<>();
//        search tag using "like"
        qw.like("tag_name", keyword);
        List<Tag> tags = tagService.list(qw);
        List<Integer> tagIdList = new ArrayList<>();
        for (Tag tag : tags
        ) {
            log.info("add by tag:" + tag.getTagName());
            tagIdList.add(tag.getTagId());
        }
        List<RecipeTagBridge> recipeTagBridges = new ArrayList<>();
        if (tagIdList.size() != 0) {
            QueryWrapper<RecipeTagBridge> qw2 = new QueryWrapper<>();
            qw2.in("tag_id", tagIdList);
            recipeTagBridges = recipeTagBridgeService.list(qw2);
        }

        for (RecipeTagBridge recipeTagBridge : recipeTagBridges
        ) {

            recipeIdSet.add(recipeTagBridge.getRecipeId());
        }
        return recipeIdSet;
    }

    private Set<Integer> searchRecipeIdListByTitle(String keyword) {
        QueryWrapper<Recipe> qw = new QueryWrapper<>();
        Set<Integer> recipeIdSet = new HashSet<>();
//        search tag using "like"
        qw.like("recipe_name", keyword);
        List<Recipe> recipes = list(qw);
        for (Recipe r : recipes
        ) {
            recipeIdSet.add(r.getRecipeId());
        }
        return recipeIdSet;
    }

    private Set<Integer> searchRecipeIdListByIngredient(String keyword) {
        QueryWrapper<Ingredient> qw = new QueryWrapper<>();
        Set<Integer> recipeIdSet = new HashSet<>();
//        search tag using "like"
        qw.like("ingredient_name", keyword);
        List<Ingredient> ingredients = ingredientService.list(qw);
        List<Integer> ingredientIdList = new ArrayList<>();
        for (Ingredient ingredient : ingredients
        ) {
            log.info("add by ingredient:" + ingredient.getIngredientName());
            ingredientIdList.add(ingredient.getIngredientId());
        }
        List<IngredientRecipeBridge> ingredientRecipeBridges = new ArrayList<>();
        if (ingredientIdList.size() != 0) {
            QueryWrapper<IngredientRecipeBridge> qw2 = new QueryWrapper<>();
            qw2.in("ingredient_id", ingredientIdList);
            ingredientRecipeBridges = ingredientRecipeBridgeService.list(qw2);
        }
        for (IngredientRecipeBridge ingredientRecipeBridge : ingredientRecipeBridges
        ) {
            recipeIdSet.add(ingredientRecipeBridge.getRecipeId());
        }
        return recipeIdSet;
    }

    @Override
    public RecipeResultVo getResultVoById(int id) {
        Recipe recipe = getById(id);
        RecipeResultVo vo = new RecipeResultVo(recipe);
        vo.setId(id);
        vo.setUrl(generateRecipeUrl(id));
        vo.setCoverPath(imgService.getPathById(recipe.getRecipeCoverId()));
        vo.setAuthor(userService.getUsernameById(recipe.getRecipeAuthorId()));
        List<Tag> tagList = recipeTagBridgeService.getTagsByRecipeId(recipe.getRecipeId());
        vo.setTagList(tagList);
        return vo;
    }

    private List<RecipeResultVo> getResultVoListByIdList(Set<Integer> idList) {
        List<RecipeResultVo> voList = new ArrayList<>();
        for (Integer id : idList
        ) {
            voList.add(getResultVoById(id));
        }
        return voList;
    }

    private String generateRecipeUrl(int id) {
        return "/recipe/" + id;
    }

    @Override
    public List<RecipeResultVo> getResultVoListByUserId(int id) {
        QueryWrapper<Recipe> qw = new QueryWrapper<>();
        qw.eq("recipe_author_id", id);
        List<Recipe> recipes = list(qw);
        List<RecipeResultVo> voList = new ArrayList<>();
        if (recipes == null) {
            return voList;
        }
        for (Recipe r : recipes
        ) {
            voList.add(getResultVoById(r.getRecipeId()));
        }
        return voList;

    }


}


