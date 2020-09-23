package com.ditto.cookiez.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Step extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "step_id", type = IdType.AUTO)
    private Integer stepId;

    private Integer stepOrder;

    private Integer imgId;

    private Integer recipeId;

    private String stepContent;


}
