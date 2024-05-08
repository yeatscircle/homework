package com.hit.homework.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
public class CoursePlanDto implements Serializable {
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
    private LocalTime timePeriod;

    /**
     * 授课老师
     */
    private List<Long> teachers;

    /**
     * 上课教室
     */
    private String location;

    /**
     *
     */
    private LocalDate createTime;

    /**
     *
     */
    private LocalDate updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
