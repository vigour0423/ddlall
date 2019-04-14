package com.ddl.learn.concurrency.designpattern.Volatile;


public class VolatileTest {

    private static  int INIT_VALUE = 0;

    private final static int MAX_LIMIT = 5;

    private static Object moditor = new Object();

    /**
     * 这里除了模型的原因，还有就是Java做了优化，这里只有读的操作，认为不需要去更新主内存，所以从cache拿
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT) {
               // synchronized (moditor) {
                    if (localValue != INIT_VALUE) {
                        System.out.printf("The value updated to [%d]\n", INIT_VALUE);
                        localValue = INIT_VALUE;
                    }
            //    }
            }
        }, "READER").start();

        Thread.sleep(1000);
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (INIT_VALUE < MAX_LIMIT) {
               // synchronized (moditor) {
                    System.out.printf("Update the value to [%d]\n", ++localValue);
                    INIT_VALUE = localValue;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
           // }
        }, "UPDATER").start();
    }
}