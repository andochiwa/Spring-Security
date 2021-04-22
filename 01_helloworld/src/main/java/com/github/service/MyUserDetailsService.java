package com.github.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HAN
 * @version 1.0
 * @create 04-22-23:20
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<com.github.entity.User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", username);
        com.github.entity.User user = userService.getOne(wrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        List<GrantedAuthority> role =
                AuthorityUtils.commaSeparatedStringToAuthorityList("admins"); // 需要和权限一致

        return new User(user.getName(),
                new BCryptPasswordEncoder().encode(user.getPassword()),
                role);
    }
}
