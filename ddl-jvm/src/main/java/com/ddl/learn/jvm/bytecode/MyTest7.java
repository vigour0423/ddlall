package com.ddl.learn.jvm.bytecode;

/**
 * 现在JVM在执行Java代码的时候，通常都会将解释执行与编译执行二者结合起来进行。
 * <p>
 * 所谓解释执行，就是通过解释器来读取字节码，遇到相应的指令就去执行该指令。
 * 所谓的编译执行，就是通过即时编译器（just in time JIT）将字节码转换为本地机器码来执行；现代JVM会
 * 根据代码热点来生成相应的本地机器码。
 * <p>
 * 基于栈的指令集与基于寄存器的指令集之间的关系：
 * <p>
 * 1.JVM执行指令所采取的方式是基于栈的指令集。
 * 2.基于栈的指令集主要的操作有入栈和出栈两种。
 * 3.基于栈的指令集的优势在于它可以在不同平台之间移植，而基于寄存器的指令集是与硬件的架构紧密关联的，无法做到可移植。
 * 4.基于栈的指令集的缺点在于完全相同的操作，指令数量通常要比基于寄存器的指令集数量更多；基于栈的指令集是在内存中完成操作的，
 * 而基于寄存器的指令集是直接由CPU来执行的，它是在高速缓冲区中进行执行的，速度要快很多。虽然虚拟机可以采用一些优化手段，但总体
 * 来说，基于栈的指令集的执行速度要慢一些。
 * @author liuddl
 * @version 1.0
 * @date 2019-05-15 10:59:02
 */
public class MyTest7 {

    public int myCalculate() {
        int a = 1;
        int b = 2;
        int c = 3;
        int d = 4;
        int result = (a + b - c) * d;
        return result;
    }
}
