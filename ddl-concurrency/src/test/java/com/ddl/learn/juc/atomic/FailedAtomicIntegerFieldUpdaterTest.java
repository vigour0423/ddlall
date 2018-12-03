package com.ddl.learn.juc.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class FailedAtomicIntegerFieldUpdaterTest {

    /**
     * Can't access the private field of object
     */
    @Test(expected = RuntimeException.class)
    public void testPrivateFieldAccessError() {
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        TestMe me = new TestMe();
        System.out.println(updater.get(me));
        updater.compareAndSet(me, 0, 1);
    }

    @Test
    public void testTargetObjectIsNull() {
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        updater.compareAndSet(null, 0, 1);
    }

    @Test
    public void testFieldNameInvalid() {
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i1");
        updater.compareAndSet(null, 0, 1);
    }


    @Test
    public void testFieldTypeInvalid() {
        AtomicReferenceFieldUpdater<TestMe2, Integer> updater = AtomicReferenceFieldUpdater.newUpdater(TestMe2.class, Integer
                .class, "i");
        TestMe2 me = new TestMe2();

        boolean b = updater.compareAndSet(me, null, 1);
        System.out.println(b);
        System.out.println(updater.get(me));
    }

    @Test
    public void testFieldIsNotVolatile() {
        AtomicReferenceFieldUpdater updater = AtomicReferenceFieldUpdater.newUpdater(TestMe2.class, Integer.class, "i");
        TestMe me = new TestMe();

        updater.compareAndSet(me, null, 1);
    }

    static class TestMe2 {
        volatile Integer i;
    }

}
