package com.ditto.cookiez.service.impl;

import com.ditto.cookiez.entity.Like;
import com.ditto.cookiez.mapper.LikeMapper;
import com.ditto.cookiez.service.ILikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  Service Class
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-16
 */
@Service
public abstract class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements ILikeService {

    @Override
    public Like getByUserID(String userId) {
        return null;
    }
}
