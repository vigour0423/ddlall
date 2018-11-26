package com.ddl.learn.concurrency.designpattern.chapter4;


public interface LifeCycleListener {

    void onEvent(ObservableRunnable.RunnableEvent event);
}
