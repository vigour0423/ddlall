package com.ddl.juc.collections.blocking;

import com.ddl.concurrency.juc.collections.blocking.LinkedTransferQueueExample;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


public class LinkedTransferQueueExampleTest {

    /**
     * Transfers the element to a waiting consumer immediately, if possible.
     * Question:
     * when return the false that means at this time no consumer waiting, how about the element?
     * will store into the queue?
     * <p>
     * Answer:
     * Without enqueuing the element.
     */
    @Test
    public void testTryTransfer() {
        LinkedTransferQueue<String> queue = LinkedTransferQueueExample.create();
        boolean result = queue.tryTransfer("Transfer");
        assertThat(result, equalTo(false));
        assertThat(queue.size(), equalTo(0));
    }


    @Test
    public void testTransfer() throws InterruptedException {
        LinkedTransferQueue<String> queue = LinkedTransferQueueExample.create();

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {
            try {
                assertThat(queue.take(), equalTo("Transfer"));
            } catch (InterruptedException e) {
                fail();
            }
        }, 1, TimeUnit.SECONDS);
        executorService.shutdown();
        long currentTime = System.currentTimeMillis();

        queue.transfer("Transfer");
        assertThat(queue.size(), equalTo(0));
        assertThat((System.currentTimeMillis() - currentTime) >= 1000, equalTo(true));
    }


    @Test
    public void testTransfer2() throws InterruptedException {
        LinkedTransferQueue<String> queue = LinkedTransferQueueExample.create();
        assertThat(queue.getWaitingConsumerCount(), equalTo(0));
        assertThat(queue.hasWaitingConsumer(), equalTo(false));

        List<Callable<String>> collect = IntStream.range(0, 5).boxed().map(i -> (Callable<String>) () -> {
            try {
                return queue.take();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(toList());


        ExecutorService executorService = Executors.newCachedThreadPool();
        collect.forEach(executorService::submit);
        TimeUnit.MILLISECONDS.sleep(100);
        assertThat(queue.getWaitingConsumerCount(), equalTo(5));
        assertThat(queue.hasWaitingConsumer(), equalTo(true));

        IntStream.range(0, 5).boxed().map(String::valueOf).forEach(queue::add);

        TimeUnit.MILLISECONDS.sleep(5);
        assertThat(queue.getWaitingConsumerCount(), equalTo(0));
        assertThat(queue.hasWaitingConsumer(), equalTo(false));
    }

    @Test
    public void testAdd() throws InterruptedException {
        LinkedTransferQueue<String> queue = LinkedTransferQueueExample.create();
        assertThat(queue.add("Hello"), equalTo(true));
        assertThat(queue.size(), equalTo(1));

    }

}