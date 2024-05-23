package com.hit.homework.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hit.homework.conmon.Result;

import com.hit.homework.domain.GenderCount;
import com.hit.homework.domain.Students;
import com.hit.homework.mapper.StuMapper;
import com.hit.homework.service.ClassesService;
import com.hit.homework.service.StuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Tag(name = "员工信息指令操作")
@Slf4j
@RestController
@RequestMapping("/stu")
@AllArgsConstructor
public class StuController {
    final private StuService stuService;

    @Operation(summary = "批量删除操作")
    @DeleteMapping("/{ids}")
    public Result deleteStu(@PathVariable List<Long> ids) {
        if (stuService.removeAndCla(ids)) {
            return Result.success();
        }
        return Result.error("删除失败");
    }

    @Operation(summary = "分页查询所有学生数据")
    @GetMapping
    public Result selectAll(String name, Short gender, Long cla,
                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("name={},gender={},cla={},page={},pageSize={},startTime={},endTime={}", name, gender, cla, page, pageSize, begin, end);
        // 分页构造器
        Page<Students> pageInfo = new Page<>(page, pageSize);

        //条件构造器
        LambdaQueryWrapper<Students> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        lambdaQueryWrapper.like(!StringUtils.isEmpty(name), Students::getName, name);
        lambdaQueryWrapper.eq(!Objects.isNull(cla), Students::getClassId, cla);
        lambdaQueryWrapper.eq(!Objects.isNull(gender), Students::getGender, gender);
        lambdaQueryWrapper.between(!Objects.isNull(begin) && !Objects.isNull(end), Students::getCreateTime, begin, end);

        //执行操作
        stuService.page(pageInfo, lambdaQueryWrapper);

        return Result.success(pageInfo);
    }

    @Operation(summary = "添加学生信息")
    @PostMapping
    public Result addStu(@RequestBody Students stu) {
        log.info("stu={}", stu);
        if (stuService.saveAndCla(stu))
            return Result.success();
        return Result.error("添加失败");
    }

    @Operation(summary = "根据id查询学生信息")
    @GetMapping("/{id}")
    public Result getStu(@PathVariable Long id) {
        Students stu = stuService.getById(id);
        return Result.success(stu);
    }

    @Operation(summary = "更新学生信息")
    @PutMapping
    public Result updateStu(@RequestBody Students stu) {
        if (stuService.checkInfo(stu))
            return Result.success();
        return Result.error("更新失败");
    }

    @Operation(summary = "获取所有学生的gender信息")
    @GetMapping("/Repo")
    public Result getRepo() {
        return Result.success(stuService.getGenderInfo());
    }

}
