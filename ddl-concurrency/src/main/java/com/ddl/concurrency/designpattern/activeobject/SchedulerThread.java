package com.ddl.concurrency.designpattern.activeobject;

/**
 * 负责将Proxy的异步方法所创建的MethodRequest实例存入其维护的缓冲区中。并根据一定的调 度策略，
 * 对其维护的缓冲区中的MethodRequest实例进行执行。其调度策略可以根据实际需要来定，
 * 如FIFO、LIFO和根据 MethodRequest中包含的信息所定的优先级等。
 */
public class SchedulerThread extends Thread {

    private final ActivationQueue activationQueue;

    public SchedulerThread(ActivationQueue activationQueue) {
        this.activationQueue = activationQueue;
    }

    public void invoke(MethodRequest request) {
        this.activationQueue.put(request);
    }

    @Override
    public void run() {
        while (true) {
            activationQueue.take().execute();
        }
    }
}
