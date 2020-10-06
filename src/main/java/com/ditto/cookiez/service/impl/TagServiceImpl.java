package com.ditto.cookiez.service.impl;

import com.baomidou.mybatisplus.core.assist.ISqlRunner;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ditto.cookiez.entity.Recipe;
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
    @Autowired
    private RecipeMapper recipeMapper;
    private final Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);

    private final QueryWrapper<Recipe> queryWrapper = new QueryWrapper<>();

    private QueryWrapper<Tag> qw = new QueryWrapper<>();

    @Override
    public Integer existedReturnId(String tagName) {
        qw.clear();
        qw.eq("tag_name", tagName);
        Tag tag = getOne(qw);
        if (tag != null) {
            return tag.getTagId();
        } else {
            return -1;
        }
    }
}
