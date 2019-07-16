package com.ddl.jvm.bytecode;

/**
 * @author liuddl
 * @version 1.0
 * @date 2019-05-15 16:28:59
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("From real subject");
    }
}
