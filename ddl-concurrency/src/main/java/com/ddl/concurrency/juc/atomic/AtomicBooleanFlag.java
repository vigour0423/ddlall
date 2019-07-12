package com.ddl.concurrency.juc.atomic;


import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanFlag {

    private static AtomicBoolean flag = new AtomicBoolean();

    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                while (flag.get()) {
               /*     try {
                        Thread.sleep(1000);
                        System.out.println("I am working.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
                System.out.println("I am finished.");
            }
        }.start();

        Thread.sleep(5000);

        new Thread() {
            @Override
            public void run() {
                   flag.set(false);

            }
        }.start();

    }
}