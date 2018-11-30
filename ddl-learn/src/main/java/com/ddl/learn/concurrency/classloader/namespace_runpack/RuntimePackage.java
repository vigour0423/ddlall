package com.ddl.learn.concurrency.classloader.namespace_runpack;


public class RuntimePackage {
    //RuntimePackage
    //com.wangwenjun.concurrent.classloader.chapter5
    //Boot.Ext.App.com.wangwenjun.concurrent.classloader.chapter5

    //Boot.Ext.App.com.wangwenjun.concurrent.classloader.chapter5.SimpleClassLoaderTest
    //Boot.Ext.App.SimpleClassLoader.com.wangwenjun.concurrent.classloader.chapter5.SimpleClassLoaderTest

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        SimpleClassLoader simpleClassLoader = new SimpleClassLoader();
        Class<?> aClass = simpleClassLoader.loadClass("com.ddl.concurrency.classloader.chapter5.SimpleObject");
        //sSystem.out.println(aClass.getClassLoader());
        SimpleObject simpleObject = (SimpleObject) aClass.newInstance();
    }
}
