package com.ddl.learn.jvm.classloader.loadclass;

/**
 * description:
 * @author liuddl
 * @version 1.0
 * @date 2019-04-24 23:07:37
 */
public class MyTest1 {
    public static void main(String[] args) {
        String[] strings = new String[2];
        System.out.println(strings.getClass().getClassLoader());
        System.out.println("-----------");
        MyTest1[] myTest1s = new MyTest1[2];
        System.out.println(myTest1s.getClass().getClassLoader());
        System.out.println("-----------");
        int[] ints = new int[2];
        System.out.println(ints.getClass().getClassLoader());
    }
}
