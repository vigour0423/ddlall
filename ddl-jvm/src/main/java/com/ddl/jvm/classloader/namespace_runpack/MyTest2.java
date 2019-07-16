package com.ddl.jvm.classloader.namespace_runpack;

import com.ddl.jvm.classloader.customloader.MyClassLoader;

import java.lang.reflect.Method;

/**
 *类加载器的双亲委托模型的好处：
 * 1.可以确保Java核心库的类型安全：所有的Java应用都至少会引用java.lang.Object类，也就是说在运行期，Java.lang.Object这个类
 * 会被加载到Java虚拟机中；如果这个加载过程是由Java应用自己的类加载器所完成的，那么很可能就会在JVM中存在多个版本的java.lang.Object
 * 类，而且这些类之间还是不兼容的，相互不可见。（正是命名空间发挥着作用）。
 * 借助双亲委托机制，Java核心类库中的类加载工作都是由启动类加载器来统一完成的，从而确保了Java应用所使用的都是同个版本的Java核心类库，
 * 他们之间相互兼容。
 * 2.可以确保Java核心类库所提供的类不会被自定义的类所替代。
 * 3.不同的类加载器可以为相同名称（binary name） 的类创建额外的命名空间。相同名称的类可以并存在Java虚拟机中，只需要用不同的类加载器来加载他们即可。
 *
 * description:
 * @author liuddl
 * @version 1.0
 * @date 2019-05-06 16:44:14
 */
public class MyTest2 {
    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader1 = new MyClassLoader("loader1");
        MyClassLoader classLoader2 = new MyClassLoader("loader2");

        classLoader1.setDir("D:\\ddllearn\\classloader2");
        classLoader2.setDir("D:\\ddllearn\\classloader2");

        Class<?> class1 = classLoader1.loadClass("com.ddl.jvm.classloader.namespace_runpack.MyPerson");
        Class<?> class2 = classLoader2.loadClass("com.ddl.jvm.classloader.namespace_runpack.MyPerson");

        System.out.println(class1==class2);

        Object object1 = class1.newInstance();
        Object object2 = class2.newInstance();

        Method method = class1.getMethod("setPerson", Object.class);
        method.invoke(object1, object2);


    }
}
