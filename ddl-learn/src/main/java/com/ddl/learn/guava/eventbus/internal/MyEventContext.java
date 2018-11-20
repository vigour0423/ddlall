package com.ddl.learn.guava.eventbus.internal;

import java.lang.reflect.Method;


public interface MyEventContext {

    String getSource();

    Object getSubscriber();

    Method getSubscribe();

    Object getEvent();
}
