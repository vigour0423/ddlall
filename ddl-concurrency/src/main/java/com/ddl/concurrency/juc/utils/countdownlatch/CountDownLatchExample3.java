package com.ddl.concurrency.juc.utils.countdownlatch;

import scala.concurrent.Await;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class CountDownLatchExample3 {

    public static void main(String[] args)  {
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

        //latch.await();
        boolean await = true;
        try {
             await = latch.await(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println(await);
            e.printStackTrace();
        }
        System.out.println("=============");
        latch.countDown();

    }
}
