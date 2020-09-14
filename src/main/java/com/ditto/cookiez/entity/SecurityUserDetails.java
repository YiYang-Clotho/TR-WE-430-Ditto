package com.ditto.cookiez.entity;



import com.ditto.cookiez.mapper.UserMapper;
import com.ditto.cookiez.service.impl.UserServiceImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SecurityUserDetails  implements UserDetails {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Autowired
    private UserServiceImpl userServiceImp;
    @Autowired
    private UserMapper userMapper;
    public SecurityUserDetails(String username,String password, Collection<? extends GrantedAuthority> authorities){
//     should   get data form database
        this.authorities = authorities;
        this.setUsername(username);
        String encode = new BCryptPasswordEncoder().encode(password);
        this.setPassword(encode);
        this.setAuthorities(authorities);
    }
    /**
     * 账户是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否禁用
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否启用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}