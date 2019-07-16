package com.ddl.infrastructure.eventbus;


import org.springframework.messaging.MessageHandler;

/**
 * description:<事件订阅>
 * @author dongdongliu
 * @date 2018/10/25 9:50
 */
@SuppressWarnings("UnusedDeclaration")
public interface EventSubscriber extends MessageHandler {

    Class<?> getEventType();

    boolean isAsync();
}
