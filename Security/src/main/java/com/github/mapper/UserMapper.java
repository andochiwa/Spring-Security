package com.github.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author HAN
 * @version 1.0
 * @create 04-23-1:26
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
