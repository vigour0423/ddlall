package com.ddl.concurrency.designpattern.future;


public interface Future<T> {

    T get() throws InterruptedException;

}