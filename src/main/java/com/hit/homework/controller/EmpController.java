package com.hit.homework.controller;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hit.homework.conmon.Result;
import com.hit.homework.domain.Emp;
import com.hit.homework.service.EmpService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@Slf4j
@RestController
@RequestMapping("/emps")
@AllArgsConstructor
public class EmpController {
    final EmpService empService;

    @GetMapping
    public Result selectAll(String name, Integer gender, Integer page, @JsonFormat Integer pageSize, @JsonFormat LocalDateTime startTime, LocalDateTime endTime) {
        // 分页构造器
        Page<Emp> pageInfo = new Page<>();

        //条件构造器
        LambdaQueryWrapper<Emp> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        lambdaQueryWrapper.like(!StringUtils.isEmpty(name), Emp::getName, name);
        lambdaQueryWrapper.eq(Emp::getGender, gender);
        lambdaQueryWrapper.between(Emp::getCreateTime, startTime, endTime);

        //执行操作
        empService.page(pageInfo, lambdaQueryWrapper);

        return Result.success(pageInfo);
    }
}
