package com.ddl.learn.concurrency.designpattern.activeobject;


public class Test {

    //A a-> B b
    //main
    public static void main(String[] args) {
        //System.gc();
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();

        new MakerClientThread(activeObject, "Alice").start();
        new MakerClientThread(activeObject, "Bobby").start();

        new DisplayClientThread("Chris", activeObject).start();
    }
}
