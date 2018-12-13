package com.ddl.learn.concurrency.juc.utils.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class CountDownLatchExample3 {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        final Thread mainThread = Thread.currentThread();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();

               // mainThread.interrupt();
            }
        }.start();

        latch.await();
        //latch.await(1000, TimeUnit.MILLISECONDS);
        System.out.println("=============");
        latch.countDown();

    }
}
