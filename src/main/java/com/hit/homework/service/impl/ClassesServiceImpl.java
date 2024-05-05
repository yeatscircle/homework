package com.hit.homework.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hit.homework.domain.Classes;
import com.hit.homework.service.ClassesService;
import com.hit.homework.mapper.ClassesMapper;
import org.springframework.stereotype.Service;

/**
* @author yeats
* @description 针对表【classes】的数据库操作Service实现
* @createDate 2024-05-05 14:30:07
*/
@Service
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes>
    implements ClassesService{

}




