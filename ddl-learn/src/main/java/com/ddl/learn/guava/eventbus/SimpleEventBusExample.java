package com.ddl.learn.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.ddl.learn.guava.eventbus.listeners.SimpleListener;


public class SimpleEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new SimpleListener());
        System.out.println("post the simple event.");
        eventBus.post("Simple Event");
    }
}
