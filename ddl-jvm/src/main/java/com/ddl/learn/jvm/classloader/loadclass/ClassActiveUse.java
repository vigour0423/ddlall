package com.ddl.learn.jvm.classloader.loadclass;

import java.util.Random;

/**
 * -XX:+TraceClassLoading,用于追踪类的加载信息并打印出来
 * -XX:+TraceClassUnloading,用于追踪类的卸载信息并打印出来
 * <p>
 * -XX:+<option> 开启
 * -XX:-<option> 关闭
 * -XX:<option>=<value> 关闭
 * <p>
 * 助记符：
 * ldc:表示将int，float或是string类型的常量值从常量池中推送到栈顶。
 * bipush表示将单字节（-128~127）的常量推送到栈顶。
 * sipush表示将短整型常量值（-32768~32767）推送到栈顶
 * iconst_1表示将int类型1推送到栈顶（iconst_1~iconst_5）
 * anewarray表示创建一个引用类型的数组，并将其引用值压入栈顶
 * newarray表示创建一个原始类型的数组，并将其引用值压入栈顶
 */
public class ClassActiveUse {

    static {
        System.out.println("ClassActiveUse");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //new Obj();
        System.out.println(ISub.b);
        //System.out.println(SubC.a);
        //System.out.println(Obj.salary);
        //Obj.printSalary();

        //Class.forName("com.ddl.learn.concurrency.classloader.chapter1.Obj");


        //System.out.println(Child.age);

        //(1)通过子类访问父类的static变量，不会导致子类的初始化.
        //System.out.println(C.salary);

        //(2）对于数组实例来说，其类型是由JVM在运行期动态生成的，表示为[Lcom.ddl.learn.jvm.classloader.loadclass.Obj这种形式。
        // 动态生成的类型，其父亲类型就是object。定义引用数组，不会初始化类
       /* Obj[] arrays = new Obj[10];
        System.out.println(arrays.getClass());

        int[] i = new int[1];
        System.out.println(i.getClass());
        System.out.println(i.getClass().getSuperclass());*/

        //(3)final修饰的常量会在编译期间会存入到调用这个常量的方法所在类的常量池中，本质上，调用类并没有直接引用到定义常量的类，因此不会初始化类。
        //这里把常量存放到ClassActiveUse常量池中，之后ClassActiveUse与Obj就没有任何关系了。甚至可以把Obj的class文件删除
        //System.out.println(Obj.salary);
        //(4)final修饰的复杂类型，在编译期间无法计算得出，那么不会存入到调用这个常量的方法所在类的常量池中，这时在程序运行时，会导致主动使用这个常量所在的类，显然会
        // 导致这个类初始化。
        //System.out.println(Child.x);
    }


}

class Obj {

    public static final long salary = 100000L;

    public static final int x = new Random().nextInt(100);

    static {
        System.out.println("Obj 被初始化.");
    }


    public static void printSalary() {
        System.out.println("========Obj=printSalary");
    }
}


class Child extends Obj {

    public static int age = 32;

    static {
        System.out.println("Child 被初始化.");
    }
}

interface I {
    Thread thread = new Thread() {
        {
            System.out.println("I invoke");
        }
    };
   // int a = 10;
}

interface ISub extends I {
    Thread thread = new Thread() {
        {
            System.out.println("ISub invoke");
        }
    };

    static int b = new Random().nextInt(4);
    static int c = 4;
}

interface C {
    public static Thread thread = new Thread() {
        {
            System.out.println("MyParent invoke");
        }
    };
}

class SubC implements C {
    public static int a = 3;
}


//访问某个类或者接口的静态变量，或者对该静态变量进行赋值操作
//1.对某个类的静态变来那个进行读写 ->class
//2.对接口中静态变量进行读取      ->interface
