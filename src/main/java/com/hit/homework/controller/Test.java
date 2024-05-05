package com.hit.homework.controller;


import com.hit.homework.conmon.Result;
import com.hit.homework.domain.Dept;
import com.hit.homework.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class Test {
    final DeptService deptService;

    @GetMapping("/index")
    public Result index() {
        return Result.success(deptService.list());
    }
}
