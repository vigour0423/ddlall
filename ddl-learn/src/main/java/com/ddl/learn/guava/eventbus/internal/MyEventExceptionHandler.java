package com.ddl.learn.guava.eventbus.internal;


public interface MyEventExceptionHandler {
    void handle(Throwable cause, MyEventContext context);
}
