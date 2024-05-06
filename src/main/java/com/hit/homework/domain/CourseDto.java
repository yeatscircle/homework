package com.hit.homework.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class CourseDto {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String serialNumber;

    private String description;

    private ArrayList<String> teachers;

    private String classes;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
