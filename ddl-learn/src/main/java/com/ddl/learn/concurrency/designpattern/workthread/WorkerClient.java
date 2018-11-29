package com.ddl.learn.concurrency.designpattern.workthread;


public class WorkerClient {
    public static void main(String[] args) {
        //工人流水线模式

        final Channel channel = new Channel(5);
        channel.startWorker();

        new TransportThread("Alex", channel).start();
        new TransportThread("Jack", channel).start();
        new TransportThread("William", channel).start();
    }
}