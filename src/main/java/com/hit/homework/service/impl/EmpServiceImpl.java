package com.hit.homework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hit.homework.domain.CourseRelationship;
import com.hit.homework.domain.Emp;
import com.hit.homework.mapper.EmpMapper;
import com.hit.homework.service.CourseRelationshipService;
import com.hit.homework.service.EmpService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {
    final CourseRelationshipService courseRelationshipService;

    @Override
    @Transactional
    public boolean checkInfo(List<Long> ids) {
        for (Long id : ids) {
            LambdaQueryWrapper<CourseRelationship> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(CourseRelationship::getEmpId, id);
            if (courseRelationshipService.count(wrapper) > 0)
                return false;
        }
        return this.removeByIds(ids);
    }
}
