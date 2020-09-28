package com.ditto.cookiez.mapper;

import com.ditto.cookiez.entity.RecipeTagBridge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
public interface RecipeTagBridgeMapper extends BaseMapper<RecipeTagBridge> {
    @Select("selec")
    List<RecipeTagBridge> getRolePermissions(String roleCode);
}
