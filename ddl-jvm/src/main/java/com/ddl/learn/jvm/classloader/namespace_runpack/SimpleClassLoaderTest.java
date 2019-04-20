package com.ddl.learn.jvm.classloader.namespace_runpack;


public class SimpleClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        SimpleClassLoader simpleClassLoader = new SimpleClassLoader();
        Class<?> aClass = simpleClassLoader.loadClass("com.ddl.learn.jvm.classloader.namespace_runpack.SimpleObject");
        System.out.println(aClass.getClassLoader());

    }
}
