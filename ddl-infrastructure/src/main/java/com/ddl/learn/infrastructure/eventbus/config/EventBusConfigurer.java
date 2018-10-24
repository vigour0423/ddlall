package com.ddl.learn.infrastructure.eventbus.config;


import com.ddl.learn.infrastructure.eventbus.components.EventBusBuilder;

public interface EventBusConfigurer {

    void configureEventBus(EventBusBuilder eventBusBuilder);
}
