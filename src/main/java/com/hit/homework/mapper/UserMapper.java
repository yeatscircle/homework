package com.hit.homework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hit.homework.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User>{
}
