package com.ddl.learn.concurrency.designpattern.future;


public interface Future<T> {

    T get() throws InterruptedException;

}