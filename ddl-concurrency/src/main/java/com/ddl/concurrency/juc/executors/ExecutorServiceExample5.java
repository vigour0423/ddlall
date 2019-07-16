package com.ddl.concurrency.juc.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class ExecutorServiceExample5 {
    public static void main(String[] args) {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        executorService.getQueue().add(() -> System.out.println("I am added directly into the queue"));
        //executorService.execute(() -> System.out.println("I will be process because of triggered the execute."));

    }
}
