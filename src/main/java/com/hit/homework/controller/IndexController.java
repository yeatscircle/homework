package com.hit.homework.controller;


import com.hit.homework.conmon.Result;
import com.hit.homework.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class IndexController {
    final DeptService deptService;

    @GetMapping("/index")
    public Result index() {
        return Result.success("Success!!!");
    }
}
