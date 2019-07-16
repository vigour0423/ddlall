package com.ddl.guava.eventbus.internal;

@FunctionalInterface
public interface MyEventExceptionHandler {
    void handle(Throwable cause, MyEventContext context);
}
