package com.ddl.concurrency.juc.utils.countdownlatch;

import java.util.concurrent.CountDownLatch;


public class CountDownLatchExample2 {

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch latch = new CountDownLatch(1);

        new Thread() {
            @Override
            public void run() {
                System.out.println("Do some initial working.");
                try {
                    Thread.sleep(1000);
                    latch.await();
                    System.out.println("Do other working...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    latch.await();
                    System.out.println("release.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                System.out.println("asyn prepare for some data.");
                try {
                    Thread.sleep(2000);
                    System.out.println("data prepare for done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }

            }
        }.start();

        //Thread.currentThread().join();

    }
}
