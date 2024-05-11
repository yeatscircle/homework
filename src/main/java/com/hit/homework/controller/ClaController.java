package com.hit.homework.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hit.homework.conmon.Result;
import com.hit.homework.domain.Classes;
import com.hit.homework.domain.Course;
import com.hit.homework.domain.Students;
import com.hit.homework.service.ClassesService;
import com.hit.homework.service.CourseService;
import com.hit.homework.service.StuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "班级信息指令操作")
@Slf4j
@RestController
@RequestMapping("/cla")
@RequiredArgsConstructor
public class ClaController {
    final ClassesService classesService;
    final CourseService courseService;
    final StuService stuService;


    @Operation(summary = "查询所有班级信息")
    @GetMapping
    public Result selectAll() {
        return Result.success(classesService.list());
    }

    @Operation(summary = "删除班级信息")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id) {

        LambdaQueryWrapper<Students> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Students::getClassId, id);
        if (stuService.count(lqw) > 0)
            return Result.error("班级内存在学生,删除失败");

        LambdaQueryWrapper<Course> lqw1 = new LambdaQueryWrapper<>();
        lqw1.eq(Course::getClassId, id);
        if (courseService.count(lqw1) > 0)
            return Result.error("班级存在安排课程现象,删除失败");

        if (classesService.removeById(id))
            return Result.success();
        return Result.error("删除失败");
    }

    @Operation(summary = "提交班级信息")
    @PostMapping
    public Result add(@RequestBody Classes classes) {
        log.info(classes.toString());
        if (classesService.save(classes))
            return Result.success();
        return Result.error("存储失败");
    }

    @Operation(summary = "根据id查询班级信息")
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Long id) {
        return Result.success(classesService.getById(id));
    }

    @Operation(summary = "修改班级信息")
    @PutMapping
    public Result update(@RequestBody Classes classes) {
        if (classesService.updateById(classes))
            return Result.success();
        return Result.error("更新失败");
    }


    @Operation(summary = "返回统计班级信息")
    @GetMapping("/Rep")
    public Result getRep() {
        return Result.success(classesService.list());
    }
}
