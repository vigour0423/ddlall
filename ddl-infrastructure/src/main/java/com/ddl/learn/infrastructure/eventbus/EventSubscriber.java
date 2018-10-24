package com.ddl.learn.infrastructure.eventbus;


import org.springframework.messaging.MessageHandler;

@SuppressWarnings("UnusedDeclaration")
public interface EventSubscriber extends MessageHandler {

    Class<?> getEventType();

    boolean isAsync();
}
