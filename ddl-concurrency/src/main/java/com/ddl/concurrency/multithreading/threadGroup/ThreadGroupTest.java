package com.ddl.concurrency.multithreading.threadGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author liuddl
 * @version 1.0
 * @date 2019-06-01 16:22:25
 */
public class ThreadGroupTest {
    public static void main(String[] args) {
        int num = 10;
        ThreadGroup threadGroup = new ThreadGroup("test-group") {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("ThreadGroup捕获到线程异常 - " + e.getMessage());
            }
        };

        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread thread = new Thread(threadGroup, new MyThread(), "threadname-" + i);
            threadList.add(thread);
        }

        System.out.println("运行前线程组中活跃线程数 -> " + threadGroup.activeCount());
        System.out.println("开始运行所有线程...");
        for (Thread t : threadList) {
            t.start();
        }
        //获取线程组中所有[活动]线程
        Thread[] threads = new Thread[num];
        threadGroup.enumerate(threads);
        for (Thread t : threads) {
            System.out.println("线程名-" + t.getName());
        }
        System.out.println("所有线程运行后,线程组中活跃线程数-" + threadGroup.activeCount());
        //不断的查看线程组中活跃的线程数
        Thread thread = new Thread(() -> {
            int num1;
            try {
                while ((num1 = threadGroup.activeCount()) > 0) {
                    System.out.println("当前线程组活跃线程数为 -> " + num1);
                    TimeUnit.SECONDS.sleep(1);
                }
                System.out.println("All Thread HAS FINISHED");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
