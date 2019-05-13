package com.ddl.learn.jvm.classloader.unloadclass;

import com.ddl.learn.jvm.classloader.customloader.MyClassLoader;

import java.util.concurrent.TimeUnit;

/**
 * description: 卸载
 * @author liuddl
 * @version 1.0
 * @date 2019-04-29 20:47:04
 */
public class UnloadClass {
    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader = new MyClassLoader("MyClassLoader");
        classLoader.setDir("D:\\ddllearn\\classloader2");
        System.out.println(classLoader);
        Class<?> aClass = classLoader.loadClass("com.ddl.learn.jvm.classloader.loadclass.LoaderClass");
        Object instance = aClass.newInstance();
        System.out.println(instance.hashCode());
        classLoader = null;
        aClass = null;
        instance = null;
        System.gc();

        TimeUnit.HOURS.sleep(1);
        classLoader = new MyClassLoader("MyClassLoader");
        classLoader.setDir("D:\\ddllearn\\classloader2");
        System.out.println(classLoader);
        aClass = classLoader.loadClass("com.ddl.learn.jvm.classloader.loadclass.LoaderClass");
        instance = aClass.newInstance();
        System.out.println(instance.hashCode());

    }
}
