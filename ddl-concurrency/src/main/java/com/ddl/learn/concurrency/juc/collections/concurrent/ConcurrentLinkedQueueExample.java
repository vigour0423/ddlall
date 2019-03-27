package com.ddl.learn.concurrency.juc.collections.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;


public class ConcurrentLinkedQueueExample {
    public static void main(String[] args) {

        final ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 100000; i++) {
            queue.offer(System.nanoTime());
        }

        System.out.println("===== offer done===== ");

        long startTime = System.currentTimeMillis();
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
        System.out.println(System.currentTimeMillis() - startTime);


    }

    private static void handleText(String s) {
        if (null != s && !"".equals(s)) {

        }

        //
        if (null != s && s.length() > 0) {

        }
        if (null != s && !s.isEmpty()) {

        }
    }
}
/**
 * 61883
 * 2909
 */
