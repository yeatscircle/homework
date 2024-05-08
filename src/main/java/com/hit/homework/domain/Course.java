package com.hit.homework.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @TableName course_plan
 */

@Data
public class Course implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 课程名
     */
    private String name;

    /**
     * 课程序号
     */
    private String serialNumber;

    /**
     * 班级号
     */
    private Long classId;

    /**
     * 课程时间安排
     */
    private Integer week;

    /**
     * 课程时间
     */
    private Date timePeriod;

    /**
     * 上课教室
     */
    private String location;


    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDate createTime;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDate updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}