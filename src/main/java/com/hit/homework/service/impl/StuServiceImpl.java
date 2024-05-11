package com.hit.homework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hit.homework.conmon.Result;
import com.hit.homework.domain.Classes;
import com.hit.homework.domain.GenderCount;
import com.hit.homework.domain.Students;
import com.hit.homework.mapper.StuMapper;
import com.hit.homework.service.ClassesService;
import com.hit.homework.service.StuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<GenderCount> getGenderInfo() {
        List<GenderCount> genderCounts = new ArrayList<>();
        GenderCount gMale = new GenderCount();
        gMale.setGender(1);
        gMale.setGenderCount(0);

        GenderCount gFemale = new GenderCount();
        gFemale.setGender(2);
        gFemale.setGenderCount(0);

        List<Students> students = this.list();
        for (Students stu : students) {
            if (stu.getGender() == 1) {
                gMale.setGenderCount(gMale.getGenderCount() + 1);
            }else
                gFemale.setGenderCount(gFemale.getGenderCount() + 1);
        }
        genderCounts.add(gMale);
        genderCounts.add(gFemale);
        return genderCounts;
    }
}
