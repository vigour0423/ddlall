package com.ddl.learn.guava.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.ddl.learn.guava.eventbus.listeners.SimpleListener;

import java.util.concurrent.Executor;


public class AsyncEventBusExample {
    public static void main(String[] args) {
        AsyncEventBus eventBus = new AsyncEventBus(new SeqExecutor());
        eventBus.register(new SimpleListener());
        eventBus.post("hello");

    }

    static class SeqExecutor implements Executor {

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }
}
