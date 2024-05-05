package com.hit.homework.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

/**
 * 
 * @TableName classes
 */
@TableName(value ="classes")
@Data
public class Classes implements Serializable {
    /**
     * 班级id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 班级序号
     */
    private String name;

    /*
    * 班级人数
     */
    private Integer number;

    /**
     * 班级创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDate createTime;

    /**
     * 信息更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDate updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}