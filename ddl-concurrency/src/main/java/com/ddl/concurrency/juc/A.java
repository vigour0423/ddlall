package com.ddl.concurrency.juc;


public class A {

    private int i = 0;

    public A() {
        this.i = 1;
    }

    public int get() {
        return this.i;
    }
}
