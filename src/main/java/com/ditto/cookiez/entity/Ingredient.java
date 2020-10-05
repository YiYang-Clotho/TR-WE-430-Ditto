package com.ditto.cookiez.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Ingredient extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ingredient_id", type = IdType.AUTO)
    private Integer ingredientId;

    private String ingredientName;

    public Ingredient() {
    }

    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
