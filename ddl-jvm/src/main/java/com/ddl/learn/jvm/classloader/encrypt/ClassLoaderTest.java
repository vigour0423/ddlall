package com.ddl.learn.jvm.classloader.encrypt;

import java.lang.reflect.Method;


public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {

        DecryptClassLoader classLoader = new DecryptClassLoader();
        Class<?> aClass = classLoader.loadClass("com.ddl.learn.jvm.classloader.customloader.MyObject");
        System.out.println(aClass);

        Object obj = aClass.newInstance();
        Method method = aClass.getMethod("hello");
        method.invoke(obj);


    }
}