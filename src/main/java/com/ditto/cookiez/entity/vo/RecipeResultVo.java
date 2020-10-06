package com.ditto.cookiez.entity.vo;

import com.ditto.cookiez.entity.Tag;
import lombok.Data;

import java.util.List;

/**
 * @author Zhihao Liang
 * @date 2020/10/5 16:54
 * @email s3798366@student.rmit.edu.au
 */
@Data
public class RecipeResultVo {
    private String recipeName;
    private String recipeDescription;
    private String author;
    private String coverPath;
    private String url;
    private List<Tag> tagList;

    public RecipeResultVo() {
    }

    public RecipeResultVo(String recipeName, String recipeDescription) {
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;

    }

    public RecipeResultVo(String recipeName, String recipeDescription, String author) {
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.author = author;
    }
}
