package com.ddl.learn.jvm.classloader.bootclassloader;


public class BootClassLoader {

    public static void main(String[] args) throws ClassNotFoundException {
      /*  System.getProperties().list(System.out);*/
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));


        Class<?> klass = Class.forName("com.ddl.learn.jvm.classloader.bootclassloader.SimpleObject");
        System.out.println(klass.getClassLoader());
        System.out.println(klass.getClassLoader().getParent());
        System.out.println(klass.getClassLoader().getParent().getParent());


        Class<?> clazz = Class.forName("java.lang.String");
        System.out.println(clazz);
        System.out.println(clazz.getClassLoader());


    }
}
