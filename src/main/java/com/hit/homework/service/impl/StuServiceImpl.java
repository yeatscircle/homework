package com.hit.homework.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hit.homework.domain.Students;
import com.hit.homework.mapper.StuMapper;
import com.hit.homework.service.StuService;
import org.springframework.stereotype.Service;

@Service
public class StuServiceImpl extends ServiceImpl<StuMapper, Students> implements StuService  {
}
