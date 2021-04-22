package com.github.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author HAN
 * @version 1.0
 * @create 04-22-22:57
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 设置自定义登录页面
                .loginPage("/login.html") // 登录页面设置
                .loginProcessingUrl("/login") // 登录访问路径
                .defaultSuccessUrl("/hello").permitAll() // 登录成功后的地址
                .and().authorizeRequests()
                .antMatchers("/", "/hi", "/login").permitAll() // 设置无需登录即可可访问路径
//                .antMatchers("/hello").hasAuthority("admins") // 针对单个权限
                .antMatchers("/hello").hasAnyAuthority("admins", "roles") // 针对多个权限
                .anyRequest().authenticated()
                .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 加密密码

//        auth.userDetailsService(userDetailsService);

//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 加密密码
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String andochiwa = passwordEncoder.encode("andochiwa");
//
//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder)
//                .withUser("andochiwa").password(andochiwa).roles("");
//    }
}
