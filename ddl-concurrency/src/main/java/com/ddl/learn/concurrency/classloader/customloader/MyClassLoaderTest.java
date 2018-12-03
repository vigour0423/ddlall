package com.ddl.learn.concurrency.classloader.customloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        MyClassLoader classLoader = new MyClassLoader("MyClassLoader");
        Class<?> aClass = classLoader.loadClass("com.ddl.learn.concurrency.classloader.customloader.MyObject");
        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());
        System.out.println(classLoader.getClass().getClassLoader());

        Object obj = aClass.newInstance();
        Method method = aClass.getMethod("hello");
        method.invoke(obj);


    }
}
