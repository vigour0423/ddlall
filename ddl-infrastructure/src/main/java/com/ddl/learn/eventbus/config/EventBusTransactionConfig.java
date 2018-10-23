package com.ddl.learn.eventbus.config;

import com.ddl.learn.eventbus.components.EventBusBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class EventBusTransactionConfig implements EventBusConfigurer {

    @Autowired(required = false)
    private PlatformTransactionManager transactionManager;

    @Override
    public void configureEventBus(EventBusBuilder eventBusBuilder) {
        if (transactionManager != null) {
            eventBusBuilder.setTransactionManager(transactionManager);
        }
    }

}
