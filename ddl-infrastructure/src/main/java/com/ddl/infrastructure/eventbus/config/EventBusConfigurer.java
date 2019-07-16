package com.ddl.infrastructure.eventbus.config;


import com.ddl.infrastructure.eventbus.components.EventBusBuilder;

public interface EventBusConfigurer {

    void configureEventBus(EventBusBuilder eventBusBuilder);
}
