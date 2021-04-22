package com.github.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.entity.User;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @author HAN
 * @version 1.0
 * @create 04-23-1:26
 */
@MapperScan
public interface UserMapper extends BaseMapper<User> {
}
