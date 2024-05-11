package com.hit.homework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hit.homework.domain.Classes;
import com.hit.homework.domain.Students;
import com.hit.homework.mapper.StuMapper;
import com.hit.homework.service.ClassesService;
import com.hit.homework.service.StuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class StuServiceImpl extends ServiceImpl<StuMapper, Students> implements StuService {
    final ClassesService classesService;

    @Override
    @Transactional
    public boolean saveAndCla(Students stu) {
        this.save(stu);
        Classes cla = classesService.getById(stu.getClassId());
        cla.setNumber(cla.getNumber() + 1);
        classesService.updateById(cla);
        return true;
    }

    @Transactional
    @Override
    public boolean checkInfo(Students stu) {
        Students student = this.getById(stu.getId());
        Classes cla1 = classesService.getById(student.getClassId());
        Classes cla2 = classesService.getById(stu.getClassId());
        if (cla1.getId() == cla2.getId())
            this.saveOrUpdate(stu);
        else {
            cla2.setNumber(cla2.getNumber() + 1);
            cla1.setNumber(cla1.getNumber() - 1);
            classesService.updateById(cla1);
            classesService.updateById(cla2);
            this.saveOrUpdate(stu);
        }
        return true;
    }
}
