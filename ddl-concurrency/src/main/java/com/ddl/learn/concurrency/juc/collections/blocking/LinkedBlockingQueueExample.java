package com.ddl.learn.concurrency.juc.collections.blocking;

import java.util.concurrent.LinkedBlockingQueue;


public class LinkedBlockingQueueExample {

    public <T> LinkedBlockingQueue<T> create() {
        return new LinkedBlockingQueue<>();
    }

    public <T> LinkedBlockingQueue<T> create(int capacity) {
        return new LinkedBlockingQueue<>(capacity);
    }
}
