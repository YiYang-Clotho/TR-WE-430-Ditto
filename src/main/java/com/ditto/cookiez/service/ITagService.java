package com.ditto.cookiez.service;

import com.ditto.cookiez.entity.Recipe;
import com.ditto.cookiez.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-16
 */
public interface ITagService extends IService<Tag> {
    Integer existedReturnId(String tagName);
}
