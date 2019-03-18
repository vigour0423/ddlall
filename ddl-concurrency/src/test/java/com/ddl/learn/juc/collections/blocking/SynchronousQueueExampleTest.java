package com.ddl.learn.juc.collections.blocking;

import com.ddl.learn.concurrency.juc.collections.blocking.SynchronousQueueExample;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


public class SynchronousQueueExampleTest {

    @Test
    public void testAdd() throws InterruptedException {
        SynchronousQueue<String> queue = SynchronousQueueExample.create();

        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                assertThat(queue.take(), equalTo("SynchronousQueue"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        TimeUnit.MILLISECONDS.sleep(5);
        assertThat(queue.add("SynchronousQueue"), equalTo(true));
    }

    /**
     * Producer Consumer
     * <p>
     * P------>queue<-----Consumer
     * @throws
     */
    @Test
    public void testOffer() throws InterruptedException {
        SynchronousQueue<String> queue = SynchronousQueueExample.create();

        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                assertThat(queue.take(), equalTo("SynchronousQueue"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        TimeUnit.MILLISECONDS.sleep(5);
        assertThat(queue.offer("SynchronousQueue"), equalTo(true));

    }


    /**
     * Producer Consumer
     * <p>
     * P------>queue<-----Consumer
     * @throws
     */
    @Test
    public void testPut() throws InterruptedException {
        SynchronousQueue<String> queue = SynchronousQueueExample.create();
/*
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                assertThat(queue.take(), equalTo("SynchronousQueue"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        TimeUnit.MILLISECONDS.sleep(5);*/
        queue.put("SynchronousQueue");
        fail("should not process to here.l");
    }

}