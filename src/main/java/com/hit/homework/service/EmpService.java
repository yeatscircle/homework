package com.hit.homework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hit.homework.domain.Emp;

import java.util.List;

public interface EmpService extends IService<Emp> {
    boolean checkInfo(List<Long> ids);
}
