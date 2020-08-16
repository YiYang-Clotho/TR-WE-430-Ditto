package com.autoGenerate.service.impl;

import com.autoGenerate.model.entity.Favorite;
import com.autoGenerate.mapper.FavoriteMapper;
import com.autoGenerate.service.IFavoriteService;
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
