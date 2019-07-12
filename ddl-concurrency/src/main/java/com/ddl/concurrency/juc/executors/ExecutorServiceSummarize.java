package com.ddl.concurrency.juc.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceSummarize {

    public static void main(String[] args) throws InterruptedException {
        final List<Thread> threads = new ArrayList<>();
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(10, r -> {
            Thread t = new Thread(r);
            threads.add(t);
            return t;
        });


        executorService.execute(() -> System.out.println("="));
        executorService.execute(() -> System.out.println("="));
        executorService.execute(() -> System.out.println("="));
        executorService.execute(() -> System.out.println("="));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(threads.size());
        //System.out.println(executorService.getActiveCount());

        //threads.stream().forEach(Thread::interrupt);
        executorService.execute(() -> System.out.println("="));
        executorService.execute(() -> System.out.println("="));
        executorService.execute(() -> System.out.println("="));
        executorService.execute(() -> System.out.println("="));

        TimeUnit.SECONDS.sleep(1);
        System.out.println(threads.size());

        threads.stream().forEach(Thread::interrupt);
        executorService.execute(() -> System.out.println("="));
        executorService.execute(() -> System.out.println("="));
        executorService.execute(() -> System.out.println("="));
        executorService.execute(() -> System.out.println("="));

        TimeUnit.SECONDS.sleep(1);
        System.out.println(threads.size());
    }
}
