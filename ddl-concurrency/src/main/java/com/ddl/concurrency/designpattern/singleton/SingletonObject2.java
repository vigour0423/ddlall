package com.ddl.concurrency.designpattern.singleton;


public class SingletonObject2 {

    private static SingletonObject2 instance;

    private SingletonObject2() {
        //empty
    }

    public static SingletonObject2 getInstance() {
        if (null == instance)
            instance = new SingletonObject2();

        return SingletonObject2.instance;
    }
}
