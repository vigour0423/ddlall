package com.ddl.learn.concurrency.designpattern.Volatile;


public class VolatileTest3 {

    private static volatile int INIT_VALUE = 0;

    private final static int MAX_LIMIT = 500;

    private static volatile boolean flag;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            boolean local = false;
            while (true) {
                if (local != flag) {
                    System.out.println("T1->" + flag);
                }
            /*    try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }, "ADDER-1").start();

        Thread.sleep(3000);

        new Thread(() -> {
            flag = true;
        }, "ADDER-2").start();
    }
}
