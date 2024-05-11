package com.hit.homework.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hit.homework.domain.Course;
import com.hit.homework.service.CourseService;
import com.hit.homework.mapper.CourseMapper;
import org.springframework.stereotype.Service;

/**
* @author yeats
* @description 针对表【course】的数据库操作Service实现
* @createDate 2024-05-06 22:35:56
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService {

}




