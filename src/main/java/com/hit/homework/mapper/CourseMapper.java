package com.hit.homework.mapper;

import com.hit.homework.domain.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author yeats
* @description 针对表【course】的数据库操作Mapper
* @createDate 2024-05-06 22:35:56
* @Entity com.hit.homework.domain.Course
*/
@Mapper
public interface CourseMapper extends BaseMapper<Course> {
}




