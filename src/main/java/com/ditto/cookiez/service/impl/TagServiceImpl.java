package com.ditto.cookiez.service.impl;

import com.baomidou.mybatisplus.core.assist.ISqlRunner;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ditto.cookiez.entity.Recipe;
import com.ditto.cookiez.entity.RecipeTagBridge;
import com.ditto.cookiez.entity.Tag;
import com.ditto.cookiez.entity.User;
import com.ditto.cookiez.mapper.RecipeMapper;
import com.ditto.cookiez.mapper.TagMapper;
import com.ditto.cookiez.mapper.UserMapper;
import com.ditto.cookiez.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.ditto.cookiez.service.IRecipeService;
import com.ditto.cookiez.service.IRecipeTagBridgeService;
import com.ditto.cookiez.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * Service Class
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-16
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {
    @Resource
    private RecipeMapper recipeMapper;
    private final Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);

    private final QueryWrapper<Recipe> queryWrapper = new QueryWrapper<>();

    private QueryWrapper<Tag> qw = new QueryWrapper<>();
    @Autowired
    IRecipeTagBridgeService recipeTagBridgeService;
    @Autowired
    IRecipeService recipeService;
    @Resource
    TagMapper mapper;

    @Override
    public Integer existedReturnId(String tagName) {
        qw.clear();
        qw.eq("tag_name", tagName);
        List<Tag> list= list(qw);
        Tag tag = getOne(qw);
        if (tag != null) {
            return tag.getTagId();
        } else {
            return -1;
        }
    }
    @Override
    public boolean save(Tag tag){
        qw.clear();
        qw.eq("tag_name", tag.getTagName());
        List<Tag> list= list(qw);
        if (list.size() >=1) {
            tag.setTagId(list.get(0).getTagId());
        } else {
           mapper.insert(tag);
        }
        return true;
    }

}
