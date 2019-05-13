package com.ddl.learn.jvm.bytecode;

/**
 * @author liuddl
 * @version 1.0
 * @date 2019-05-12 19:38:46
 */
public class MyTest2 {
    String str = "Welcome";

    private int x = 5;

    private static Integer in = 10;

    public static void main(String[] args) {
        MyTest2 myTest2 = new MyTest2();
        myTest2.setX(8);
        in = 20;
    }

    public int getX() {
        return x;
    }

    private synchronized void setX(int x) {
        this.x = x;
    }

    private void test(String str) {
        synchronized (str) {
            System.out.println("hello world");
        }
    }

    private synchronized static void test2() {
    }
}
