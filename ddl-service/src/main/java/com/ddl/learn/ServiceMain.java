package com.ddl.learn;

import com.ddl.learn.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * description: 服务启动
 * @author dongdongliu
 * @version 1.0
 * @date 2018-10-23 11:04:52
 */
public class ServiceMain {
    public static void main(String[] args) {
         new AnnotationConfigApplicationContext(AppConfig.class);
    }
}
