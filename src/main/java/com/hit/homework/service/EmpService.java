package com.hit.homework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hit.homework.domain.DeptCount;
import com.hit.homework.domain.Emp;
import com.hit.homework.domain.GenderCount;

import java.util.List;

public interface EmpService extends IService<Emp> {
    boolean checkInfo(List<Long> ids);

    List<GenderCount> getGenderInfo();

    List<DeptCount> getDeptInfo();
}
