package com.hit.homework.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hit.homework.conmon.Result;
import com.hit.homework.domain.Emp;
import com.hit.homework.service.EmpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Tag(name = "员工信息指令操作")
@Slf4j
@RestController
@RequestMapping("/emps")
@AllArgsConstructor
public class EmpController {
    final EmpService empService;

    @Operation(summary = "分页查询所有员工数据")
    @GetMapping
    public Result selectAll(String name, Short gender,
                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("name={},gender={},page={},pageSize={},startTime={},endTime={}", name, gender, page, pageSize, begin, end);
        // 分页构造器
        Page<Emp> pageInfo = new Page<>(page, pageSize);

        //条件构造器
        LambdaQueryWrapper<Emp> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        lambdaQueryWrapper.like(!StringUtils.isEmpty(name), Emp::getName, name);
        lambdaQueryWrapper.eq(!Objects.isNull(gender), Emp::getGender, gender);
        lambdaQueryWrapper.between(!Objects.isNull(begin)&&!Objects.isNull(end),Emp::getEntrydate, begin, end);

        //执行操作
        empService.page(pageInfo, lambdaQueryWrapper);

        return Result.success(pageInfo);
    }

    @Operation(summary = "批量删除操作")
    @DeleteMapping("/{ids}")
    public Result deleteEmp(@PathVariable List<Long> ids){
        if (empService.checkInfo(ids))
            return Result.success();
        return Result.error("存在职工任课现象,无法删除");
    }

    @Operation(summary = "添加员工信息")
    @PostMapping
    public Result addEmp(@RequestBody Emp emp){
        if (empService.save(emp))
            return Result.success();
        return  Result.error("添加失败");
    }

    @Operation(summary = "根据id查询员工信息")
    @GetMapping("/{id}")
    public Result getEmp(@PathVariable Long id){
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @Operation(summary = "更新员工信息")
    @PutMapping
    public Result updateEmp(@RequestBody Emp emp){
        if (empService.updateById(emp))
            return Result.success();
        return Result.error("更新失败");
    }

    @Operation(summary = "得到所有信息")
    @GetMapping("/all")
    public Result getAllEmp(){
        return Result.success(empService.list());
    }
}
