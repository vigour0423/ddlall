package com.ddl.learn.config;

import com.google.common.eventbus.AsyncEventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author dongdongliu
 * @date 2018/10/23 9:45
 */
@Configuration
@ComponentScan(basePackages = "com.ddl.learn")
public class AppConfig {


    /**
     * description:<线程池配置>
     * @author dongdongliu
     * @date 2018/10/23 10:27
     */
    @Bean(name = "taskExecutor")
    public ThreadPoolTaskExecutor getThreadPool() {
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setCorePoolSize(20);
        poolTaskExecutor.setMaxPoolSize(200);
        poolTaskExecutor.setQueueCapacity(1000000);
        poolTaskExecutor.setKeepAliveSeconds(600);
        poolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return poolTaskExecutor;
    }

    /**
     * description:<事件总线>
     * @author dongdongliu
     * @date 2018/10/23 10:51
     */
    @Bean(name = "eventBus")
    public AsyncEventBus getEventBus() {
        return new AsyncEventBus(getThreadPool());
    }


}
