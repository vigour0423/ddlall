package com.ddl.guava.eventbus.test;

import com.ddl.guava.eventbus.internal.MyEventBus;

public class MyEventBusExample {
    public static void main(String[] args) {
        MyEventBus myEventBus = new MyEventBus((cause, context) ->
        {
            //cause.printStackTrace();
            System.out.println("==========================================");
            System.out.println(context.getSource());
            System.out.println(context.getSubscribe());
            System.out.println(context.getEvent());
            System.out.println(context.getSubscriber());
        });
        //myEventBus.register(new MySimpleListener());
        myEventBus.register(new MySimpleListener2());
        myEventBus.post(123131, "alex-topic");
        //myEventBus.post(123131, "test-topic");



/*        MyEventBus myEventBus1 = new MyEventBus();
        myEventBus1.register(new MySimpleListener());
        myEventBus1.register(new MySimpleListener2());
        myEventBus1.post(123131, "test-topic");*/


    }
}
