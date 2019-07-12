package com.ddl.jvm.bytecode;

import java.lang.reflect.Proxy;

/**
 * @author liuddl
 * @version 1.0
 * @date 2019-05-15 21:09:48
 */
public class Client {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        RealSubject rs = new RealSubject();
        DynamicSubject dynamicSubject = new DynamicSubject(rs);
        Class<? extends RealSubject> aClass = rs.getClass();
        Subject subject = (Subject) Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), dynamicSubject);

        subject.request();

        System.out.println(subject.getClass());

        System.out.println(subject.getClass().getSuperclass());
    }
}
