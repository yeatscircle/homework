package com.hit.homework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hit.homework.domain.Students;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface StuMapper extends BaseMapper<Students> {

}
