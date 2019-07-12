package com.ddl.jvm.classloader.threadcontextloader;

import java.sql.Driver;
import java.util.ServiceLoader;

/**
 * description:
 * @author liuddl
 * @version 1.0
 * @date 2019-05-08 14
 * :42:38
 */
public class MyTest3 implements Runnable {

    private Thread thread;

    public MyTest3() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        ClassLoader classLoader = this.thread.getContextClassLoader();
        this.thread.setContextClassLoader(classLoader);
        System.out.println("class:" + classLoader.getClass());
        System.out.println("parent:" + classLoader.getParent().getClass());

    }

    public static void main(String[] args) {
        //new MyTest3();
        Thread.currentThread().setContextClassLoader(MyTest3.class.getClassLoader().getParent());
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        for (Driver driver : loader) {
            System.out.println("driver:" + driver.getClass() + ",loader:" + driver.getClass().getClassLoader());
        }

        System.out.println("当前线程上下文类加载器：" + Thread.currentThread().getContextClassLoader());
        System.out.println("serviceLoader的类加载器：" + ServiceLoader.class.getClassLoader());


    }
}
