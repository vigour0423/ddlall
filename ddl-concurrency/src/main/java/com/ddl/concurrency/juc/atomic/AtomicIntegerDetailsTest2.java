package com.ddl.concurrency.juc.atomic;

public class AtomicIntegerDetailsTest2 {

    private final static CompareAndSetLock lock = new CompareAndSetLock();

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        doSomething2();
                    } catch (InterruptedException | GetLockException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    private static void doSomething() throws InterruptedException {
        synchronized (AtomicIntegerDetailsTest2.class) {
            System.out.println(Thread.currentThread().getName() + " get the lock");
            Thread.sleep(100000);
        }
    }

    private static void doSomething2() throws InterruptedException, GetLockException {
        try {
            lock.tryLock();
            System.out.println(Thread.currentThread().getName() + " get the lock");
            Thread.sleep(100000);
        } finally {
            lock.unlock();
        }
    }
}
