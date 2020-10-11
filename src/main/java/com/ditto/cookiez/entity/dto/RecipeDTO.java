package com.ditto.cookiez.entity.dto;

import com.ditto.cookiez.entity.Recipe;
import com.ditto.cookiez.entity.Tag;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhihao Liang
 * @date 2020/9/23 18:12
 * @email s3798366@student.rmit.edu.au
 */
@Data
public class RecipeDTO {
    private Integer id;
    private String recipeName;
    private String recipeDescription;
    private String author;
    private String coverPath;
    private List<IngredientDTO> ingredientDTOList;
    private List<StepDTO> stepDTOList;
    private List<Tag> tagList;

    public RecipeDTO(Recipe recipe) {
        this.recipeName = recipe.getRecipeName();
        this.recipeDescription = recipe.getRecipeDescription();
    }

    public String getTagsString() {
        String str = "";
        for (Tag tag : this.getTagList()) {
            str += tag.getTagName();
            str += ",";
        }
        str = str.substring(0, str.length() - 1);
        return str;
    }
}
