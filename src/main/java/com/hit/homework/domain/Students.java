package com.hit.homework.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;


@Data
public class Students implements Serializable {
    /**
     * 学生id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生性别,0表示女生,1表示男生
     */
    private Integer gender;

    /**
     * 学生电话
     */
    private String phone;

    /**
     * 学生班级
     */
    private Long classId;

    /**
     * 学生照片
     */
    private String image;

    /**
     * 入学时间
     */
    private LocalDate entrydate;

    /**
     * 创建时间
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