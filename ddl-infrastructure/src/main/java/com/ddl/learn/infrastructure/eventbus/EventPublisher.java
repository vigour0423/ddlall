package com.ddl.learn.infrastructure.eventbus;

public interface EventPublisher {

    void publish(Object event);
}
