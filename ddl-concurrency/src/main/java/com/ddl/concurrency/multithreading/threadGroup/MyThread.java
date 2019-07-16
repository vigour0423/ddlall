package com.ddl.concurrency.multithreading.threadGroup;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author liuddl
 * @version 1.0
 * @date 2019-06-01 16:21:33
 */
public class MyThread implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " -> start");
            TimeUnit.SECONDS.sleep(10);
            //随机发生异常
            if (ThreadLocalRandom.current().nextInt(10) > 5) {
                throw new RuntimeException(Thread.currentThread().getName() + "发生异常");
            }
            System.out.println(Thread.currentThread().getName() + " -> end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}