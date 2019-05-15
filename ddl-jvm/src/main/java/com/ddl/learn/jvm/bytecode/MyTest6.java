package com.ddl.learn.jvm.bytecode;

/**
 * 方法的动态分派：是运行期行为
 * 重要概念：方法的接收者
 * invokevirtual字节码指令的多态查找流程
 *
 *针对于方法调用动态分派的过程，虚拟机会在类的方法区建立一个虚方法表的数据结构（virtual method table ,vtable）
 *针对于invokeinterface指令来说，虚拟机会建立一个叫做接口方法的数据结构（interface method table ,itable）
 * @author liuddl
 * @version 1.0
 * @date 2019-05-14 21:35:57
 */
public class MyTest6 {
    public static void main(String[] args) {
        Fruit apple = new Apple();
        Fruit orange = new Orange();
        apple.test();
        orange.test();

        apple = new Orange();
        apple.test();
    }
}

class Fruit {
    public void test() {
        System.out.println("Fruit");
    }
}

class Apple extends Fruit {
    @Override
    public void test() {
        System.out.println("apple");
    }
}

class Orange extends Fruit {
    @Override
    public void test() {
        System.out.println("Orange");
    }
}