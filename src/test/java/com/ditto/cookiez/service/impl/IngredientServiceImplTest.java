package com.ditto.cookiez.service.impl;

import com.ditto.cookiez.entity.Ingredient;
import com.ditto.cookiez.service.IIngredientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zhihao Liang
 * @date 2020/10/11 18:32
 * @email s3798366@student.rmit.edu.au
 */
@SpringBootTest
class IngredientServiceImplTest {
@Autowired
    IIngredientService service;
    @Test
    void getIngredientsByTagId() {
    }

    @Test
    void existedReturnId() {
    }

    @Test
    void save() {
        Ingredient ingredient=new Ingredient("new1");
        service.save(ingredient);
        assert  ingredient.getIngredientId()==206;
    }
}