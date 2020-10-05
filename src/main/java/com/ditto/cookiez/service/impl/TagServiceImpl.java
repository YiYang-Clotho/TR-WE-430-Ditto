package com.ditto.cookiez.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ditto.cookiez.entity.Tag;
import com.ditto.cookiez.mapper.TagMapper;
import com.ditto.cookiez.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
