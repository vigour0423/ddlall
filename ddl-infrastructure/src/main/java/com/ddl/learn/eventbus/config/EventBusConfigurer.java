package com.ddl.learn.eventbus.config;


import com.ddl.learn.eventbus.components.EventBusBuilder;

public interface EventBusConfigurer {

    void configureEventBus(EventBusBuilder eventBusBuilder);
}
