package com.ddl.jvm.classloader.threadcontextloader;

import com.ddl.jvm.classloader.customloader.MyClassLoader;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * 当前类加载器（Current Classloader）
 * 每个类都会使用自己的类加载器（即加载自身的类加载器）来去加载其他类（指的是所依赖的类）
 * 如果ClassX 引用了ClassY就，那么ClassX的类加载器就会去加载ClassY（前提是ClassY尚未被加载）
 *
 * 线程上下文类加载器（Context Classloader）
 * 如果没有通过setContextClassloader(Classloader cl)进行设置的话，线程将继承父线程的上下文加载器。
 * Java应用运行时的初始线程的上下文类加载器就是系统类加载器。在线程中运行的代码可以通过该类的加载器来加载类与资源。
 *
 * 线程上下文类加载器的重要性：
 * SPI（Service Provider Interface）
 * 父Classloader可以使用当前线程Thread.currentThread().getContextClassLoader()所指定的classloader加载的类。
 * 这就改变了父Classloader不能使用子Classloader或者是其他直接没有父子关系的classloader加载类的情况，即改变了双亲委托模型。
 *
 * 线程上下文类加载器就是当前线程的Current Classloader。
 *
 * 在双亲委托模型下，类加载器是由下至上，即下层的类加载器会委托上层的类加载器。但是对于SPI来说，有些接口是Java核心库所提供的，
 * 而Java核心库是由于启动类加载器来加载的，而这些接口的实现却来自不同的jar包（厂商提供），Java的启动类加载器是不会加载其他来源的
 * jar包，这样传统的双亲委托模型就无法满足SPI的这些要求。而通过给当前线程设置上下文类加载器，就可以由设置的上下文加载器来实现对接口
 * 实现类的加载。
 *
 *
 *
 */
public class ThreadContextClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);
        Enumeration<URL> resources = contextClassLoader.getResources("com/ddl/learn/jvm/classloader/");
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            System.out.println(url);
        }
        Thread.currentThread().setContextClassLoader(new MyClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());

        Class.forName("com.mysql.jdbc.Driver");//(---)
        Connection conn = DriverManager.getConnection("");
    }
}
