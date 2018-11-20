package com.ddl.learn.guava.eventbus.internal;


public interface Bus {

    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Object event);

    void post(Object Event, String topic);

    void close();

    String getBusName();
}
