package com.hit.homework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hit.homework.conmon.Result;
import com.hit.homework.domain.*;
import com.hit.homework.mapper.EmpMapper;
import com.hit.homework.service.CourseRelationshipService;
import com.hit.homework.service.DeptService;
import com.hit.homework.service.EmpService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {
    final CourseRelationshipService courseRelationshipService;
    final DeptService deptService;

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

    @Override
    public List<GenderCount> getGenderInfo() {
        List<GenderCount> genderCounts = new ArrayList<>();
        GenderCount gMale = new GenderCount();
        gMale.setGender(1);
        gMale.setGenderCount(0);

        GenderCount gFemale = new GenderCount();
        gFemale.setGender(2);
        gFemale.setGenderCount(0);

        List<Emp> emps = this.list();
        for (Emp emp : emps) {
            if (emp.getGender() == 1) {
                gMale.setGenderCount(gMale.getGenderCount() + 1);
            } else
                gFemale.setGenderCount(gFemale.getGenderCount() + 1);
        }
        genderCounts.add(gMale);
        genderCounts.add(gFemale);
        return genderCounts;
    }

    @Override
    public List<DeptCount> getDeptInfo() {
        Map<Long,Integer> map = new HashMap<>();
        List<Emp> emps = this.list();
        for (Emp emp : emps) {
            if (map.containsKey(emp.getDeptId()))
                map.put(emp.getDeptId(),map.get(emp.getDeptId()) + 1);
            else
                map.put(emp.getDeptId(),1);
        }
        List<DeptCount> deptCounts = new ArrayList<>();

        // 补全所有部门
        List<Dept> list = deptService.list();
        for (Dept dept : list) {
            if (map.containsKey(dept.getId())){
                DeptCount deptCount = new DeptCount();
                deptCount.setDeptName(dept.getName());
                deptCount.setCount(map.get(dept.getId()));
                deptCounts.add(deptCount);
            }
            else{
                DeptCount deptCount = new DeptCount();
                deptCount.setDeptName(dept.getName());
                deptCount.setCount(0);
                deptCounts.add(deptCount);
            }
        }
        return deptCounts;
    }
}
