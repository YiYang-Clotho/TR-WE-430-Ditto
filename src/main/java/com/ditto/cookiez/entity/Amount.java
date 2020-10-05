package com.ditto.cookiez.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Amount extends Model {

    private static final long serialVersionUID = 1L;
    @TableId(value = "amount_id",type = IdType.AUTO)
    private Integer amountId;

    private String amountContent;

    private Integer ingredientId;

    private Integer recipeId;

    public Amount() {
    }

    public Amount(String amountContent) {
        this.amountContent = amountContent;
    }
}
