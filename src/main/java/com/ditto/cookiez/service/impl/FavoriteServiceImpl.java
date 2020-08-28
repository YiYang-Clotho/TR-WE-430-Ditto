package com.ditto.cookiez.service.impl;

import com.ditto.cookiez.entity.Favorite;
import com.ditto.cookiez.mapper.FavoriteMapper;
import com.ditto.cookiez.service.IFavoriteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements IFavoriteService {

}
