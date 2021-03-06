package com.ditto.cookiez.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Comment extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "comment_id")
    private Integer commentId;

    private Integer userId;

    private String commentContent;

    private Integer recipeId;


}
