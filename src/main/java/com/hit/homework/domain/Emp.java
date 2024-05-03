package com.hit.homework.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Emp {
//    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private String name;
    private Short gender;
    private String image;
    private Short job;
    private LocalDate entryDate;
    private Short deptId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDate createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDate updateTime;
}
