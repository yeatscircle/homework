package com.hit.homework.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hit.homework.conmon.JwtUtils;
import com.hit.homework.conmon.Result;
import com.hit.homework.domain.Emp;
import com.hit.homework.service.EmpService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {
    final private EmpService empService;

    @Operation(summary = "注册用户")
    @PostMapping("/register")
    public Result register(@RequestBody Emp emp) {
        emp.setPassword(DigestUtils.md5DigestAsHex(emp.getPassword().getBytes()));
        if (empService.save(emp))
            return Result.success();
        return Result.error("注册失败");
    }

    @Operation(summary = "登录操作")
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        //对密码进行md5加密
        String password = emp.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //查询数据库
        LambdaQueryWrapper<Emp> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Emp::getUsername, emp.getUsername());
        Emp e = empService.getOne(wrapper);

        log.info("emp:{}",e);
        //显示查询结果
        if (e==null)
            return Result.error("用户不存在");

        //对比密码
        if(!e.getPassword().equals(password))
            return Result.error("密码错误");

        //登录成功
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",e.getId());
        claims.put("name",e.getName());
        claims.put("username",e.getUsername());
        String jwt = JwtUtils.generateJwt(claims);
        return Result.success(jwt);
    }
}
