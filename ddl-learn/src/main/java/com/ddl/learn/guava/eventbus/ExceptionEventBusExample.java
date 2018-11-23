package com.ddl.learn.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.ddl.learn.guava.eventbus.listeners.ExceptionListener;


public class ExceptionEventBusExample {
    public static void main(String[] args) {

        /*final EventBus eventBus1 = new EventBus();
        eventBus1.register(new ExceptionListener());

        eventBus1.post("exception post");*/


        final EventBus eventBus = new EventBus((exception, context) ->
        {
            System.out.println(context.getEvent());
            System.out.println(context.getEventBus());
            System.out.println(context.getSubscriber());
            System.out.println(context.getSubscriberMethod());
        });
        eventBus.register(new ExceptionListener());

        eventBus.post("exception post");
    }



}
