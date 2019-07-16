package com.ddl.concurrency.designpattern.activeobject;

/**
 * Proxy：负责对外暴露异步方法接口。当调用方代码调用该参与者实例的异步方法doSomething时，
 * 该方法会生成一个相应的MethodRequest实例并将其存储到Scheduler所维护的缓冲区中。
 * doSomething方法的返回值是一个表示其执行结果的外包装 对象：Future参与者的实例。异步方法doSomething运行在调用方代码所在的线程中。
 */
class ActiveObjectProxy implements ActiveObject {

    private final SchedulerThread schedulerThread;

    private final Servant servant;

    public ActiveObjectProxy(SchedulerThread schedulerThread, Servant servant) {
        this.schedulerThread = schedulerThread;
        this.servant = servant;
    }

    @Override
    public Result makeString(int count, char fillChar) {
        FutureResult future = new FutureResult();
        schedulerThread.invoke(new MakeStringRequest(servant, future, count, fillChar));
        return future;
    }

    @Override
    public void displayString(String text) {
        schedulerThread.invoke(new DisplayStringRequest(servant, text));
    }
}