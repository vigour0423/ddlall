package com.ddl.learn.concurrency.juc.executors;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class FutureExample2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //testIsDone();
        //testCancel();
       testCancel2();
    }

    /**
     * Completion may be due to normal termination, an exception, or
     * cancellation
     */
    private static void testIsDone() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(() -> {
            //throw new RuntimeException();
            TimeUnit.SECONDS.sleep(10);
            return 10;
        });
        try {
            //Integer result = future.get();
            //System.out.println(result);
            System.out.println(" is done " + future.isDone());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" is done " + future.isDone());
        }
    }

    /**
     * try to cancel maybe failed
     * <ul>
     * <li>task is completed already.</li>
     * <li>has already been cancelled.</li>
     * </ul>
     */
    private static void testCancel() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicBoolean running = new AtomicBoolean(true);
        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("eeeeee");
                e.printStackTrace();
            }
           /* while (running.get()) {
            }*/
            return 10;
        });
        //future.get();
        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println(future.cancel(true));
        System.out.println(future.isDone());
        System.out.println(future.isCancelled());

    }

    /**
     * try to cancel maybe failed
     * <ul>
     * <li>task is completed already.</li>
     * <li>has already been cancelled.</li>
     * </ul>
     */
    private static void testCancel2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicBoolean running = new AtomicBoolean(true);
        Future<Integer> future = executorService.submit(() -> {
         /*   try {
                TimeUnit.SECONDS.sleep(60);
                System.out.println("=======================");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            while (!Thread.interrupted()) {
                //sdfsdfs

            }
            System.out.println("1111111");
            return 10;
        });

        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println(future.cancel(true));
        System.out.println(future.isDone());
        System.out.println(future.isCancelled());
        TimeUnit.MILLISECONDS.sleep(20);
        System.out.println(future.get());

    }
}
