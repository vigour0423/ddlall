package com.ddl.learn.concurrency.classloader.namespace_runpack;


public class RuntimePackage {
    //RuntimePackage
    //com.ddl.learn.concurrency.classloader.namespace_runpack
    //Boot.Ext.App.com.ddl.learn.concurrency.classloader.namespace_runpack

    //Boot.Ext.App.com.ddl.learn.concurrency.classloader.namespace_runpack.SimpleClassLoaderTest
    //Boot.Ext.App.SimpleClassLoader.com.ddl.learn.concurrency.classloader.namespace_runpack.SimpleObject

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        SimpleClassLoader simpleClassLoader = new SimpleClassLoader();
        Class<?> aClass = simpleClassLoader.loadClass("com.ddl.learn.concurrency.classloader.namespace_runpack.SimpleObject");
        System.out.println(aClass.getClassLoader());
        SimpleObject simpleObject = (SimpleObject) aClass.newInstance();
    }
}
