package com.ddl.concurrency.multithreading.threadGroup;

import java.util.concurrent.TimeUnit;

/**
 * @author liuddl
 * @version 1.0
 * @date 2019-06-01 16:54:26
 */
public class ThreadGroupDaemon {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group1 = new ThreadGroup("group1");
        Thread thread = new Thread(group1, () -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("thread1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "group1-thread1");

        thread.start();

        ThreadGroup group2 = new ThreadGroup("group2");
        new Thread(group2, () -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("thread2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "group1-thread2").start();

        group2.setDaemon(true);

        TimeUnit.SECONDS.sleep(3);

        System.out.println(group1.isDestroyed());
        System.out.println(group2.isDestroyed());
    }
}
