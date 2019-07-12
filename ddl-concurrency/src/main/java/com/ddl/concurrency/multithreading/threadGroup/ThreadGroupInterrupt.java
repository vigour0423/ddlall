package com.ddl.concurrency.multithreading.threadGroup;

import java.util.concurrent.TimeUnit;

/**
 * @author liuddl
 * @version 1.0
 * @date 2019-06-01 16:46:29
 */
public class ThreadGroupInterrupt {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("TestGroup");
        new Thread(group, () -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(2);
                } catch (InterruptedException e) {
                    //received interrupt signal and clear quickly
                    System.out.println(Thread.currentThread().isInterrupted()+"  "+Thread.currentThread().getName());
                    break;
                }
            }
            System.out.println("t1 will exit");
        }, "t1").start();

        new Thread(group, () -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(2);
                } catch (InterruptedException e) {
                    //received interrupt signal and clear quickly
                    System.out.println(Thread.currentThread().isInterrupted()+"  "+Thread.currentThread().getName());
                    break;
                }
            }
            System.out.println("t2 will exit");
        }, "t2").start();
        //make sure all threads start
        TimeUnit.MILLISECONDS.sleep(20000);

        group.interrupt();
    }

}
