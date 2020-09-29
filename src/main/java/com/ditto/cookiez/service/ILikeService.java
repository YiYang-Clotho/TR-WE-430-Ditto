package com.ditto.cookiez.service;

import com.ditto.cookiez.entity.Like;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ditto.cookiez.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-16
 */
public interface ILikeService extends IService<Like> {
    Like getByUserID(String userId);
}
