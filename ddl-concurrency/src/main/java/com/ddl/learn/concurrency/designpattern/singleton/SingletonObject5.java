package com.ddl.learn.concurrency.designpattern.singleton;


public class SingletonObject5 {

    private static volatile SingletonObject5 instance;

    private SingletonObject5() {
        // init other .....
    }

    //double check add volatile
    public static SingletonObject5 getInstance() {

        if (null == instance) {
            synchronized (SingletonObject4.class) {
                if (null == instance)
                    instance = new SingletonObject5();
            }
        }
        return SingletonObject5.instance;
    }
}
