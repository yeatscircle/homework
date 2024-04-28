package com.hit.homework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hit.homework.domain.Emp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper extends BaseMapper<Emp> {
}
