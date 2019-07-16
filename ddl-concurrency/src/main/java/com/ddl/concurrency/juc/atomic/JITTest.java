package com.ddl.concurrency.juc.atomic;


public class JITTest {

    private static boolean init = false;

    public static void main(String[] args) throws InterruptedException {

        new Thread() {
            @Override
            public void run() {
                while (!init) {
                    // System.out.println(".");
                }
                /**
                 * while(true){}
                 *
                 * while (!init) {
                 System.out.println(".");
                 }
                 */
            }
        }.start();

        Thread.sleep(1000);

        new Thread() {
            @Override
            public void run() {
                init = true;
                System.out.println("Set init to true.");
            }
        }.start();
    }
}
