package com.ditto.cookiez.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Recipe extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "recipe_id", type = IdType.AUTO)
    private Integer recipeId;

    private String recipeName;

    private String recipeDescription;

    private Integer recipeLike;

    private LocalDateTime recipeCreatedTime;

    private  int recipeCoverId;



}
