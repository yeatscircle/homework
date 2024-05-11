package com.hit.homework.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hit.homework.conmon.Result;
import com.hit.homework.domain.Course;
import com.hit.homework.domain.CoursePlanDto;
import com.hit.homework.domain.CourseRelationship;
import com.hit.homework.service.CourseService;
import com.hit.homework.service.CourseRelationshipService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/course/plan")
public class CoursePlanController {
    final CourseService courseService;
    final CourseRelationshipService courseRelationshipService;

    @Operation(summary = "分页查询所有课程数据")
    @GetMapping
    public Result selectAll(String name, String serial_number, Integer week,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("name={},gender={},week={},page={},pageSize={}", name, serial_number, week, page, pageSize);

        //条件构造器
        LambdaQueryWrapper<Course> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        lambdaQueryWrapper.like(!StringUtils.isEmpty(name), Course::getName, name);
        lambdaQueryWrapper.eq(!StringUtils.isEmpty(serial_number), Course::getSerialNumber, serial_number);
        lambdaQueryWrapper.eq(!Objects.isNull(week), Course::getWeek, week);

        List<Course> records = courseService.list(lambdaQueryWrapper);
        List<CoursePlanDto> list = new ArrayList<>();

        for (Course record : records) {
            CoursePlanDto dto = new CoursePlanDto();
            LambdaQueryWrapper<CourseRelationship> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
            lambdaQueryWrapper1.eq(CourseRelationship::getCourseId, record.getId());
            List<Long> teachers = courseRelationshipService.list(lambdaQueryWrapper1)
                    .stream()
                    .map(CourseRelationship::getEmpId)
                    .collect(Collectors.toList());
            BeanUtils.copyProperties(record, dto);
            dto.setTeachers(teachers);
            list.add(dto);
        }

        Page<CoursePlanDto> coursePlanDtoPage = new Page<>(page, pageSize);
        coursePlanDtoPage.setRecords(list);

        return Result.success(coursePlanDtoPage);
    }


    @Operation(summary = "更新课程信息")
    @PutMapping
    public Result updateEmp(@RequestBody CoursePlanDto coursePlanDto) {
        Course course = new Course();
        BeanUtils.copyProperties(coursePlanDto, course);
        courseService.updateById(course);
        List<Long> teachers = coursePlanDto.getTeachers();
        LambdaQueryWrapper<CourseRelationship> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CourseRelationship::getCourseId, course.getId());
        courseRelationshipService.remove(lambdaQueryWrapper);
        for (Long teacher : teachers) {
            CourseRelationship relationship = new CourseRelationship();
            relationship.setCourseId(course.getId());
            relationship.setEmpId(teacher);
            courseRelationshipService.save(relationship);
        }
        return Result.success();
    }

    @Operation(summary = "根据id查询课程信息")
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        Course course = courseService.getById(id);
        CoursePlanDto dto = new CoursePlanDto();
        LambdaQueryWrapper<CourseRelationship> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper1.eq(CourseRelationship::getCourseId, course.getId());
        List<Long> teachers = courseRelationshipService.list(lambdaQueryWrapper1)
                .stream()
                .map(CourseRelationship::getEmpId)
                .collect(Collectors.toList());
        BeanUtils.copyProperties(course, dto);
        dto.setTeachers(teachers);
        return Result.success(dto);
    }
}
