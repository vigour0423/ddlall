package com.ddl.learn.concurrency.designpattern.singleton;


public class SingletonObject6 {

    private SingletonObject6() {

    }

    private static class InstanceHolder {
        private final static SingletonObject6 instance = new SingletonObject6();
    }

    public static SingletonObject6 getInstance() {
        return InstanceHolder.instance;
    }
}