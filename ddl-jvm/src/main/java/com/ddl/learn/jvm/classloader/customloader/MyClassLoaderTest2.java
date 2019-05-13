package com.ddl.learn.jvm.classloader.customloader;


/**
 * 1.类加载器的委托是优先交给父亲加载器先去尝试加载
 * 2.父加载器和子加载器其实是一种包装关系，或者包含关系
 */
public class MyClassLoaderTest2 {
    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader classLoader1 = new MyClassLoader("MyClassLoader-1");
        MyClassLoader classLoader2 = new MyClassLoader("MyClassLoader-2",classLoader1);
        classLoader1.setDir("D:\\ddllearn\\classloader2");
        classLoader2.setDir("D:\\ddllearn\\classloader2");

        Class<?> aClass = classLoader1.loadClass("com.ddl.learn.jvm.classloader.loadclass.LoaderClass");
        System.out.println(aClass.hashCode());
        Class<?> aClass2 = classLoader2.loadClass("com.ddl.learn.jvm.classloader.loadclass.LoaderClass");
        System.out.println(aClass2.hashCode());
        System.out.println(((MyClassLoader) aClass.getClassLoader()).getClassLoaderName());
    }
}