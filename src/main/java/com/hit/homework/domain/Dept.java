package com.hit.homework.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Dept {
    private Long id; //ID
    private String name; //部门名称

    @TableField(fill = FieldFill.INSERT)
    private LocalDate createTime; //创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDate updateTime; //修改时间
}
