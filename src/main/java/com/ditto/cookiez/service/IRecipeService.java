package com.ditto.cookiez.service;

import com.alibaba.fastjson.JSONObject;
import com.ditto.cookiez.entity.Recipe;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ditto.cookiez.entity.dto.RecipeDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  service class
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
public interface IRecipeService extends IService<Recipe> {
     void updateRecipe(JSONObject json);

    Recipe addRecipe(JSONObject json, Map<String, MultipartFile> fileMap) throws IOException;

    RecipeDTO getRecipe(int id);
    List<Recipe> getRecipesByTagId(int tag_id);
}
