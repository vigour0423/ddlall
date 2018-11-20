package com.ddl.learn.guava.eventbus.internal;

@FunctionalInterface
public interface MyEventExceptionHandler {
    void handle(Throwable cause, MyEventContext context);
}
