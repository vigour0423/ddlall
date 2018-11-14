package com.ddl.learn.concurrency.multithreading.runnable;


public class BankVersion2 {


    public static void main(String[] args) {
        //这种方式能够很好的把可执行逻辑单元和线程分离开
        final TicketWindowRunnable task = new TicketWindowRunnable();

        Thread windowThread1 = new Thread(task, "一号窗口");
        Thread windowThread2 = new Thread(task, "二号窗口");
        Thread windowThread3 = new Thread(task, "三号窗口");
        Thread windowThread4 = new Thread(task, "三号窗口");
        windowThread1.start();
        windowThread2.start();
        windowThread3.start();
        windowThread4.start();
    }
}
