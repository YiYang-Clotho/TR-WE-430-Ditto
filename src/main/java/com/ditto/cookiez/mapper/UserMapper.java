package com.ditto.cookiez.mapper;

import com.ditto.cookiez.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
@Component
@Repository
public interface UserMapper extends BaseMapper<User> {

}
