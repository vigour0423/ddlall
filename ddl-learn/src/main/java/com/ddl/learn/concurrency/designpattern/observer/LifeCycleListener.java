package com.ddl.learn.concurrency.designpattern.observer;


public interface LifeCycleListener {

    void onEvent(ObservableRunnable.RunnableEvent event);
}
