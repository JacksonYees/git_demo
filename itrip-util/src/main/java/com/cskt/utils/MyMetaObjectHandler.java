package com.cskt.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

/**
 * 全局的处理器，
 * 被其他模块使用
 *  MyBatisPlus自动填充处理器
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final Logger log = LoggerFactory.getLogger(MyMetaObjectHandler.class);
    
    @Override
    public void insertFill(MetaObject metaObject) {
      this.strictInsertFill(metaObject, "creationDate" , LocalDateTime::now, LocalDateTime.class);
      this.strictInsertFill(metaObject,"modifyDate",LocalDateTime::now,LocalDateTime.class);

    }


    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject,"modeifyDate",LocalDateTime::now, LocalDateTime.class);
    }
}
