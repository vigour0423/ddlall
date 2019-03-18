package com.ddl.learn.concurrency.juc.collections.blocking;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueExample {

    public static <T> SynchronousQueue<T> create() {
        return new SynchronousQueue<>();
    }

    public static void main(String[] args) {
        create().offer(new Object());
    }
}
