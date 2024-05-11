package com.hit.homework.controller;

import com.hit.homework.conmon.Result;
import com.hit.homework.domain.Course;
import com.hit.homework.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Yests
 * @since 2024-05-05
 */
@RestController
@RequestMapping("/course")
@AllArgsConstructor
public class CourseController {
    final CourseService courseService;

//    @Operation(summary = "查询所有课程信息")
//    @GetMapping
//    public Result selectAll(){
//        ArrayList<CourseDto> cd = courseService.listCd();
//        return Result.success(cd);
//    }

    @Operation(summary = "查询所有课程信息")
    @GetMapping
    public Result selectAll() {
        return Result.success(courseService.list());
    }


    @Operation(summary = "删除课程信息")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id) {
        if (courseService.removeById(id))
            return Result.success();
        return Result.error("删除失败");
    }

    @Operation(summary = "提交新的课程信息")
    @PostMapping
    public Result add(@RequestBody Course course) {
        if (courseService.save(course))
            return Result.success();
        return Result.error("存储失败");
    }

    @Operation(summary = "根据id查询课程信息")
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Long id) {
        return Result.success(courseService.getById(id));
    }

    @Operation(summary = "修改课程信息")
    @PutMapping
    public Result update(@RequestBody Course course) {
        if (courseService.updateById(course))
            return Result.success();
        return Result.error("更新失败");
    }

}
