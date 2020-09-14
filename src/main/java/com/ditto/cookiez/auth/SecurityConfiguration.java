//package com.ditto.cookiez.auth;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
///**
// * @author Zhihao Liang
// * @date 2020/9/14 17:27
// * @email s3798366@student.rmit.edu.au
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//    /**
//     * @param http
//     * @throws Exception 授权：首页所有人可以访问，功能页只有对应有权限的人才能访问
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // 请求授权的规则~
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/level1/**").hasRole("vip1")
//                .antMatchers("/level2/**").hasRole("vip2")
//                .antMatchers("/level3/**").hasRole("vip3");
//
//        // 没有权限默认会到登录页面(security内置的登录页面)，需要开启登录页面
//        //定制登录页面loginPage("/toLogin")
//        //默认表单name用户名是username，密码是password,自己定义需要usernameParameter("username").passwordParameter("password")
//        //loginProcessingUrl("/login");参数“login”与登录表单的action保持一致
//        /**
//         * 如果只配置loginPage而不配置loginProcessingUrl的话
//         * 那么loginProcessingUrl默认就是loginPage
//         * 你配置的loginPage("/toLogin") ,那么loginProcessingUrl就是"/toLogin"，相应的action也改为“/toLogin”
//         */
//        http.formLogin().loginPage("/login")
//                .usernameParameter("username").passwordParameter("password")
//                .loginProcessingUrl("/api/login");
//        //防止网站攻击 csrf，阻止get，
//        http.csrf().disable();//关闭csrf功能，解决登录失败
//        //开启注销功能，跳到首页
//        http.logout().logoutSuccessUrl("/");
//        //开启记住我功能，cookies默认保存两周，自定义接受前端参数
//        http.rememberMe().rememberMeParameter("remember");
//
//    }
//
//    //认证， springboot 2.1.x可以直接使用
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //正常的话，这些数据应该从数据库读取 这里写入内存
////        auth.jdbcAuthentication()
////        auth.inMemoryAuthentication().userDetailsPasswordManager();
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("silent").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2", "vip3")
//                .and()
//                .withUser("root").password(new BCryptPasswordEncoder().encode("1")).roles("vip1", "vip2", "vip3")
//                .and()
//                .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");
//    }
//}
