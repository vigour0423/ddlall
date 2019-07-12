package com.ddl.concurrency.designpattern.Volatile;


public class VolatileTest {

    private static volatile int INIT_VALUE = 0;

    private final static int MAX_LIMIT = 5;

    private static Object MONITOR = new Object();

    /**
     * 没有volatile,疑问1，仅仅启动的时候去主内存拿，然后每次都去cache里吗？不会有个失效然后去主存拿吗？
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT) {
                //synchronized (MONITOR) {
                if (localValue != INIT_VALUE) {
                    System.out.printf("The value updated to [%d]\n", INIT_VALUE);
                    localValue = INIT_VALUE;
                }
                //}
            }
        }, "READER").start();

        Thread.sleep(1000);
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (INIT_VALUE < MAX_LIMIT) {
                //synchronized (MONITOR) {
                System.out.printf("Update the value to [%d]\n", ++localValue);
                INIT_VALUE = localValue;
                //}
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "UPDATER").start();
    }
}