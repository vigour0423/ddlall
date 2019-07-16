package com.ddl.jvm.classloader.bootclassloader;


import com.ddl.jvm.classloader.customloader.MyClassLoader;
import com.sun.crypto.provider.AESKeyGenerator;

public class BootClassLoader {

    public static void main(String[] args) throws ClassNotFoundException {
      /*  System.getProperties().list(System.out);*/
        Object o = new Object();
        System.out.println(o.hashCode());
        System.out.println(o.getClass().getClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));


        Class<?> klass = Class.forName("com.ddl.jvm.classloader.bootclassloader.SimpleObject");
        System.out.println(klass.getClassLoader());
        System.out.println(klass.getClassLoader().getParent());
        System.out.println(klass.getClassLoader().getParent().getParent());


        Class<?> clazz = Class.forName("java.lang.String");
        System.out.println(clazz);
        System.out.println(clazz.getClassLoader());

        AESKeyGenerator aesKeyGenerator = new AESKeyGenerator();
        System.out.println(aesKeyGenerator.getClass().getClassLoader());

        System.out.println(System.getProperty("java.system.class.loader"));
        System.out.println(MyClassLoader.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());


    }
}
