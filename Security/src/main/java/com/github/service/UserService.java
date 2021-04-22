package com.github.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.entity.User;
import com.github.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author HAN
 * @version 1.0
 * @create 04-23-1:26
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}
