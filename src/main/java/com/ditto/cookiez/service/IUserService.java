package com.ditto.cookiez.service;

import com.ditto.cookiez.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
public interface IUserService extends IService<User> {
    User getByUsername(String username);
    User auth(String username, String password);
}
