package com.ddl.infrastructure.eventbus;


import com.ddl.log.datachange.DataChangeTracer;

public class AsyncTestSubscriber {

    @Subscribe(async = true)
    public void handleTestEvent(AsyncTestEvent event) {
        boolean isTracing = DataChangeTracer.isTracing();

        System.out.println("This is async event subscriber." + ".ThreadId:" + Thread.currentThread().getId());

    }
}
