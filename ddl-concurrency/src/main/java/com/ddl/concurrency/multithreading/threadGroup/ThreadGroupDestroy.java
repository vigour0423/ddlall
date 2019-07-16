package com.ddl.concurrency.multithreading.threadGroup;

/**
 * @author liuddl
 * @version 1.0
 * @date 2019-06-01 16:53:02
 */
public class ThreadGroupDestroy {

    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("TestGroup");
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        //before destroy
        System.out.println("group.isDestroyed=" + group.isDestroyed());
        mainGroup.list();

        group.destroy();
        //after destroy
        System.out.println("group.isDestroyed=" + group.isDestroyed());
        mainGroup.list();
    }

}