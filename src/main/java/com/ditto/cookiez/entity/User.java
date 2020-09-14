package com.ditto.cookiez.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
/*
* The User class created here inherits the UserDetails interface of Spring Security and becomes a security-compliant User,
* that is, by inheriting UserDetails, the relevant Security functions in Security can be implemented
* */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends Model  {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String username;

    private String userPwd;

    private Integer roleId;

    private Integer avatarId;

    @TableField(exist = false)
    private List<String> roles;
    @TableField(exist = false)
    private String accessToken;

    public User() {
    }

    public User(String username, String userPwd) {
        this.username = username;
        this.userPwd = userPwd;
    }




}
