package com.ddl.learn.juc.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertEquals;


public class AtomicLongTest {

    @Test
    public void testCreate() {
        AtomicLong l = new AtomicLong(100L);
        /**
         * 32
         * long 64
         *
         * high 32
         * low  32
         *
         *
         */
        assertEquals(100L, l.get());
    }
}
