package com.ditto.cookiez.service.impl;

import com.ditto.cookiez.entity.Tag;
import com.ditto.cookiez.mapper.TagMapper;
import com.ditto.cookiez.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-16
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

}
