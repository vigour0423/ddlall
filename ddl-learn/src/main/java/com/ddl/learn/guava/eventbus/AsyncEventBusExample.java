package com.ddl.learn.guava.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.ddl.learn.guava.eventbus.listeners.SimpleListener;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AsyncEventBusExample {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(4);
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
