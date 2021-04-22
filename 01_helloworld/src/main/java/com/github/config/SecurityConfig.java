package com.github.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

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
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 加密密码
        auth.userDetailsService(userDetailsService);
    }

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
