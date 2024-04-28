package com.hit.homework.controller;

import com.hit.homework.conmon.Result;
import com.hit.homework.domain.Dept;
import com.hit.homework.service.DeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/depts")
@RequiredArgsConstructor
public class DeptController {
    final DeptService deptService;

    @GetMapping
    public Result selectAll(){
        return Result.success(deptService.list());
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        boolean r = deptService.removeById(id);
        if(r)
            return Result.success();
        return Result.error("删除失败");
    }

    @PostMapping
    public Result add(@RequestBody Dept dept){
        boolean saved = deptService.save(dept);
        if(saved)
            return Result.success();
        return Result.error("存储失败");
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Long id){
        return Result.success(deptService.getById(id));
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        boolean R = deptService.updateById(dept);
        log.info("dept:{}",dept);
        if (R)
            return Result.success();
        return Result.error("更新失败");
    }
}
