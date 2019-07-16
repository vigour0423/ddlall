package com.ddl.jvm.bytecode;

/**
 * description: 栈帧（stack frame）
 * <p>
 * 栈帧是一种用于帮助虚拟机执行方法调用与方法执行的数据结构。
 * <p>
 * 栈帧本身是一种数据结构，封装了方法的局部变量表，动态链接信息，方法的返回地址以及操作数栈等信息。
 * <p>
 * 符合引用，直接引用
 * 有些符号引用是在类加载阶段或是第一次使用时就会转换为直接引用，这种转换叫做静态解析；另外一些符合引用则是在每次
 * 运行期转换为直接引用，这种转换叫做动态链接，这体现为Java的多态性。
 * <p>
 * 1.invokeinterface:调用接口中的方法，实际上是在运行期决定的，决定到底调用实现接口的哪个对象的特定方法。
 * 2.invokestatic：调用静态方法。
 * 3.invokespecial：调用自己的私有方法，构造方法（<init>）以及父类的方法。
 * 4.invokevirtual：调用虚方法，运行期动态查找的过程。
 * 5.invokedynamic: 动态调用方法。
 *
 * 静态解析的4种方法：
 * 1.静态方法
 * 2.父类方法
 * 3.构造方法
 * 4.私有方法（无法被重写）
 *
 * 以上4类方法称为非虚放方法，他们是在类的加载阶段就可以将符号引用转换为直接引用。
 * @author liuddl
 * @version 1.0
 * @date 2019-05-14 10:33:50
 */
public class MyTest4 {

    public static void test() {
        System.out.println("test invoked");
    }

    public static void main(String[] args) {
        test();
    }
}
