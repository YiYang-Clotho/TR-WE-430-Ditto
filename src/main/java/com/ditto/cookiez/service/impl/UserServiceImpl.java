package com.ditto.cookiez.service.impl;

import com.ditto.cookiez.entity.User;
import com.ditto.cookiez.mapper.UserMapper;
import com.ditto.cookiez.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
