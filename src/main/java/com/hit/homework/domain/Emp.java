package com.hit.homework.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Emp {
//    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Short gender;
    private String image;
    private LocalDate entrydate;
    private Long deptId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDate createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDate updateTime;
}