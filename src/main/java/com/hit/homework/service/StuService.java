package com.hit.homework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hit.homework.domain.GenderCount;
import com.hit.homework.domain.Students;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yests
 * @since 2024-05-05
 */
public interface StuService extends IService<Students> {

    boolean saveAndCla(Students stu);

    boolean checkInfo(Students stu);

    List<GenderCount> getGenderInfo();

    boolean removeAndCla(List<Long> ids);
}
