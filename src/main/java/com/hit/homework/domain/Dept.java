package com.hit.homework.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Dept {
    private Integer id; //ID
    private String name; //部门名称

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime; //创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime; //修改时间
}
