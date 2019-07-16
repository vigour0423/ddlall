package com.ddl.concurrency.designpattern.observer;


public interface LifeCycleListener {

    void onEvent(ObservableRunnable.RunnableEvent event);
}
