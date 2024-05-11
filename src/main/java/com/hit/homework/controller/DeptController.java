package com.hit.homework.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hit.homework.conmon.Result;
import com.hit.homework.domain.Dept;
import com.hit.homework.domain.Emp;
import com.hit.homework.service.DeptService;
import com.hit.homework.service.EmpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Tag(name = "部门信息指令操作")
@RestController
@RequestMapping("/depts")
@RequiredArgsConstructor
public class DeptController {
    final DeptService deptService;
    final EmpService empService;

    @Operation(summary = "查询所有部门信息")
    @GetMapping
    public Result selectAll() {
        return Result.success(deptService.list());
    }

    @Operation(summary = "删除部门信息")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id) {
        LambdaQueryWrapper<Emp> empLambdaQueryWrapper = new LambdaQueryWrapper<>();
        empLambdaQueryWrapper.eq(Emp::getDeptId, id);
        if (empService.count(empLambdaQueryWrapper) > 0)
            return Result.error("存在用户信息,删除失败");
        if (deptService.removeById(id))
            return Result.success();
        return Result.error("删除失败");
    }

    @Operation(summary = "提交新的部门信息")
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        if (deptService.save(dept))
            return Result.success();
        return Result.error("存储失败");
    }

    @Operation(summary = "根据id查询部门信息")
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Long id) {
        return Result.success(deptService.getById(id));
    }

    @Operation(summary = "修改部门信息")
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        if (deptService.updateById(dept))
            return Result.success();
        return Result.error("更新失败");
    }

}
