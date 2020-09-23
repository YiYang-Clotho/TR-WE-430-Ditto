package com.ditto.cookiez.entity.dto;

import com.ditto.cookiez.entity.Recipe;
import lombok.Data;

import java.util.List;

/**
 * @author Zhihao Liang
 * @date 2020/9/23 18:12
 * @email s3798366@student.rmit.edu.au
 */
@Data
public class RecipeDTO {

    private String recipeName;
    private String recipeDescription;
    private String author;
    private List<StepDTO> stepList;
    private List<IngredientDTO> ingredientDTOList;
    private List<StepDTO> stepDTOList;

    public RecipeDTO(Recipe recipe) {
        this.recipeName = recipe.getRecipeName();
        this.recipeDescription=recipe.getRecipeDescription();
    }
}
