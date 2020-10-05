package com.ditto.cookiez.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestBody;

@Configuration
@EnableWebSecurity// 这个注解必须加，开启Security
@EnableGlobalMethodSecurity(prePostEnabled = true)//保证post之前的注解可以使用
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    JwtAuthorizationTokenFilter authenticationTokenFilter;


    //先来这里认证一下
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoderBean());
    }

    //拦截在这配
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/h2/**").permitAll()
//                .antMatchers("/**/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/recipe/add").permitAll()
                .antMatchers("/recipe/*").permitAll()
                .antMatchers("/recipe/detail").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/tags").permitAll()
                .antMatchers("/pantry/show").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").anonymous()
                .anyRequest().authenticated()// 剩下所有的验证都需要验证
                .and()
                .csrf().disable()                      // 禁用 Spring Security 自带的跨域处理
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 定制我们自己的 session 策略：调整为让 Spring Security 不创建和使用 session
//                .antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui",
//                        "/swagger-resources", "/swagger-resources/configuration/security",
//                        "/swagger-ui.html", "/webjars/**").permitAll()
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();
        http.headers().frameOptions().disable();
/*AntMatcher
* （1）? 匹配一个字符（除过操作系统默认的文件分隔符）
（2）* 匹配0个或多个字符
（3）**匹配0个或多个目录
（4）{spring:[a-z]+} 将正则表达式[a-z]+匹配到的值,赋值给名为 spring 的路径变量.
    (PS:必须是完全匹配才行,在SpringMVC中只有完全匹配才会进入controller层的方法
* */
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        //allow Swagger URL to be accessed without authentication
//        web.ignoring().antMatchers("/v2/api-docs",//swagger api json
//                "/swagger-resources/configuration/ui",//用来获取支持的动作
//                "/swagger-resources",//用来获取api-docs的URI
//                "/swagger-resources/configuration/security",//安全选项
//                "/swagger-ui.html");
//    }
}