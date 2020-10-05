package com.ditto.cookiez.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ditto.cookiez.WebLogAspect;
import com.ditto.cookiez.auth.JwtTokenUtil;
import com.ditto.cookiez.entity.Img;
import com.ditto.cookiez.entity.User;
import com.ditto.cookiez.mapper.UserMapper;
import com.ditto.cookiez.service.IImgService;
import com.ditto.cookiez.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ditto.cookiez.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Service Class
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IImgService imgService;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public User getByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        List<User> list = userMapper.selectList(wrapper);

        if (list.size() != 0) {
            return list.get(0);
        }
        return null;
    }


    @Override
    public User auth(String username, String password) {

        User user = getByUsername(username);
        if (user != null && user.getUserPwd().equals(password)) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            final String token = jwtTokenUtil.generateToken(userDetails);
            user.setAccessToken(token);
            return user;
        }
        return null;
    }

    @Override
    public User getUserByToken(String token) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = null;
        if (username != null) {
            user = getByUsername(username);
            if (user.getAvatarId() != null) {
                user.setAvatarPath(imgService.getPathById(user.getAvatarId()));
            }
        }

        return user;
    }

    @Override
    public Boolean register(User user) {
//        Has existed the user?
        User qUser = getByUsername(user.getUsername());
        if (qUser == null) {
            save(user);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public User updateProfile(JSONObject jsonObject, Map<String, MultipartFile> fileMap) throws IOException {
        String avatarKey = "avatar";
        Integer userId = jsonObject.getInteger("userId");
        String username = jsonObject.getString("username");
        String oldPwd = jsonObject.getString("oldPassword");
        String newPwd = jsonObject.getString("newPassword");
        logger.info(username);
        User user = getById(userId);
        user.setUsername(username);
        if (!"".equals(newPwd)) {
            user.setUserPwd(newPwd);
        }


        if (fileMap.containsKey(avatarKey)) {
            MultipartFile file = fileMap.get("avatar");
            String url = FileUtil.uploadAvatarToAws(file, userId);
            Img img = new Img(url);
            imgService.save(img);
            user.setAvatarId(img.getImgId());
        }
        user.updateById();
//        After user update the profile, cookie also need update
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        user.setAccessToken(token);
        return user;
    }

}
