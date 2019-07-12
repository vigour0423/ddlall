package com.ddl.concurrency.juc.collections.blocking;

import java.util.concurrent.ArrayBlockingQueue;


public class ArrayBlockingQueueExample {

    /**
     * 1.FIFO(First in First out)
     * 2 Once created, the capacity cannot be changed.
     * @param size
     * @param <T>
     * @return
     */
    public <T> ArrayBlockingQueue<T> create(int size) {
        return new ArrayBlockingQueue<>(size);
    }
}
