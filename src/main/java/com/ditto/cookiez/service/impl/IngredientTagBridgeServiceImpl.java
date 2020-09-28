package com.ditto.cookiez.service.impl;

import com.ditto.cookiez.entity.Ingredient;
import com.ditto.cookiez.entity.IngredientTagBridge;
import com.ditto.cookiez.mapper.IngredientTagBridgeMapper;
import com.ditto.cookiez.service.IIngredientService;
import com.ditto.cookiez.service.IIngredientTagBridgeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ditto.cookiez.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-16
 */
@Service
public class IngredientTagBridgeServiceImpl extends ServiceImpl<IngredientTagBridgeMapper, IngredientTagBridge> implements IIngredientTagBridgeService {

}
