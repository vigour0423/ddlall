package com.ddl.learn.jvm.bytecode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author liuddl
 * @version 1.0
 * @date 2019-05-15 21:07:15
 */
public class DynamicSubject implements InvocationHandler {
    private Object sub;

    public DynamicSubject(Object subject) {
        this.sub = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
   /*     System.out.println(proxy);
        System.out.println(args);*/
        System.out.println("before calling:" + method);
        method.invoke(this.sub, args);
        System.out.println("after calling:" + method);
        return null;
    }
}
