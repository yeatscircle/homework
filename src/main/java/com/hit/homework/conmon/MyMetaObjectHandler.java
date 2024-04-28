package com.hit.homework.conmon;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自定义的元数据处理器
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    //先执行原controller操作,再执行公共字段,会执行覆盖操作
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段[insert]自动填充:{}", metaObject);
        Long id = BaseContext.getCurrentId();
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段[update]自动填充:{}", metaObject);
        metaObject.setValue("updateTime", LocalDateTime.now());
    }

}
