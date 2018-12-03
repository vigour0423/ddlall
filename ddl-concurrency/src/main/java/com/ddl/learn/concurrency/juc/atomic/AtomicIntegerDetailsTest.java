package com.ddl.learn.concurrency.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;


public class AtomicIntegerDetailsTest {
    public static void main(String[] args) {
        /*
         * create
         */
  /*      AtomicInteger i = new AtomicInteger();
        System.out.println(i.get());
        i = new AtomicInteger(10);
        System.out.println(i.get());

        //set
        i.set(12);
        System.out.println(i.get());
        i.lazySet(13);

        //get and add
        AtomicInteger getAndSet = new AtomicInteger(10);
        int result = getAndSet.getAndAdd(10);
        System.out.println(result);
        System.out.println(getAndSet.get());*/

/*

        final AtomicInteger getAndSet2 = new AtomicInteger(0);
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    int v = getAndSet2.addAndGet(1);
                    System.out.println(v);
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    int v = getAndSet2.addAndGet(1);
                    System.out.println(v);
                }
            }
        }.start();
*/

        AtomicInteger ai = new AtomicInteger(10);
        boolean flag = ai.compareAndSet(12, 20);
        System.out.println(ai.get());
        System.out.println(flag);
    }
}
