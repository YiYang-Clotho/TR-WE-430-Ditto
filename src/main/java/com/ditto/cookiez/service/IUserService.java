package com.ditto.cookiez.service;

import com.alibaba.fastjson.JSONObject;
import com.ditto.cookiez.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * <p>
 *  service class
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
public interface IUserService extends IService<User> {
    User getByUsername(String username);
    User auth(String username, String password);
    Boolean register(User user);
    User getUserByToken(String token);

    User updateProfile(JSONObject jsonObject, Map<String, MultipartFile> fileMap) throws IOException;
}
