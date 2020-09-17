package com.ditto.cookiez.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Like extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "like_id", type = IdType.AUTO)
    private Integer likeId;

    private Integer userId;

    private LocalDateTime likeTime;

    private Integer status;

    private Integer recipeId;


}
