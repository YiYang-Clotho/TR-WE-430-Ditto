package com.ditto.cookiez.service;

import com.alibaba.fastjson.JSONObject;
import com.ditto.cookiez.entity.Recipe;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ditto.cookiez.entity.dto.RecipeDTO;
import com.ditto.cookiez.entity.vo.RecipeResultVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  service class
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
public interface IRecipeService extends IService<Recipe> {


    //         TODO update ingredients
    Boolean updateRecipe(JSONObject json, Map<String, MultipartFile> fileMap) throws IOException;

    Recipe addRecipe(JSONObject json, Map<String, MultipartFile> fileMap) throws IOException;

    RecipeDTO getRecipe(int id);

    RecipeDTO getRecipe(Integer id);



    List<RecipeResultVo> search(List<String> keyword);

    List<RecipeResultVo> searchByIngredients(List<String> strList);


    List<RecipeResultVo> searchByTitle(List<String> strList);

    List<RecipeResultVo> searchByTags(List<String> keywords);

    RecipeResultVo getResultVoById(int id);

    List<RecipeResultVo> getResultVoListByUserId(int id);
}
