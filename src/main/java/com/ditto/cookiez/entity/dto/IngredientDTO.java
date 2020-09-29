package com.ditto.cookiez.entity.dto;

import lombok.Data;

/**
 * @author Zhihao Liang
 * @date 2020/9/23 19:16
 * @email s3798366@student.rmit.edu.au
 */
@Data
public class IngredientDTO {
    private Integer ingredientId;
    private Integer amountId;
    private String ingredientName;
    private String amount;

    public IngredientDTO(String ingredientName, String amount) {
        this.ingredientName = ingredientName;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "IngredientDTO{" +
                "ingredientId=" + ingredientId +
                ", amountId=" + amountId +
                ", ingredientName='" + ingredientName + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
