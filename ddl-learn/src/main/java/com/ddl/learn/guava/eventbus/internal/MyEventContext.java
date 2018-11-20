package com.ddl.learn.guava.eventbus.internal;

import java.lang.reflect.Method;


public interface MyEventContext {
    /**
     * eventbus名字
     * @return
     */
    String getSource();

    /**
     * 订阅者
     * @return
     */
    Object getSubscriber();

    /**
     * 订阅的方法
     * @return
     */
    Method getSubscribe();

    /**
     * 订阅的事件
     * @return
     */
    Object getEvent();
}
