package com.ditto.cookiez.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ditto.cookiez.config.AwsClient;
import com.ditto.cookiez.entity.*;
import com.ditto.cookiez.entity.dto.IngredientDTO;
import com.ditto.cookiez.entity.dto.RecipeDTO;
import com.ditto.cookiez.entity.dto.StepDTO;
import com.ditto.cookiez.mapper.RecipeMapper;
import com.ditto.cookiez.service.*;
import com.ditto.cookiez.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        String imgPath = FileUtil.PATH + "recipes/" + recipeId + "/";
        String coverKey = FileUtil.COVER;
        String stepImgPrefix = FileUtil.STEP_PREFIX;
//        save cover img
        if (fileMap.containsKey(coverKey)) {
            MultipartFile file = fileMap.get(coverKey);
            String fileNameInDB = coverKey + FileUtil.getFileType(Objects.requireNonNull(file.getOriginalFilename()));
            FileUtil.fileupload(file.getBytes(), imgPath, fileNameInDB);

            File awsFile = new File(imgPath+fileNameInDB);

            file.transferTo(awsFile);
            log.info(AwsClient.uploadToS3(awsFile, file.getOriginalFilename()));

            Img img = new Img(FileUtil.getRecipeDirRelativePath(recipeId) + fileNameInDB);
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
                stepService.addStep(step, fileMap.get(nameInFileMap), imgPath);
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
            String coverPath = FileUtil.getFileAbsolutePath(imgService.getPathById(recipe.getRecipeCoverId()));
            recipeDTO.setCoverPath(coverPath);
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
            stepDTO.setImgPath(FileUtil.getFileAbsolutePath(imgService.getPathById(step.getImgId())));
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

    @Override
    public List<Recipe> getRecipesByTagId(int tag_id) {
        List<Recipe> recipeList = getRecipesByTagId(tag_id);

        List<RecipeDTO> recipeDTOList = new ArrayList<>();
//        get title, description and cover path
        for (Recipe recipe : recipeList
        ) {
            RecipeDTO rDTO = new RecipeDTO(recipe);
            rDTO.getRecipeName();
            rDTO.getRecipeDescription();
            recipeDTOList.add(rDTO);
            if(recipe.getRecipeCoverId() != null){
                String coverPath = FileUtil.getFileAbsolutePath(imgService.getPathById(recipe.getRecipeCoverId()));
                rDTO.setCoverPath(coverPath);
            }
        }
        return recipeList;
    }
}


