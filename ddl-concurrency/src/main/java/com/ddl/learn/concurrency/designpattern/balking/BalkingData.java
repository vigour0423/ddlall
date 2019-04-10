package com.ddl.learn.concurrency.designpattern.balking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * balking:止步，场景：酒店服务，客户挥手前台服务员服务，这时从其他地方走过一个服务员承担了这服务，前台的就放弃此次服务。
 * 就是当你去获取临界区资源时，发现正有人在使用着，那么你没有阻塞
 * 而是放弃了去获取这部分的资源。你认定，别的线程已经开始了对这部分资源的，修改操作，所以你无需操作。
 */
public class BalkingData {
    private final String fileName;

    private String content;

    private final Object MODITOR = new Object();

    private volatile boolean changed;

    public BalkingData(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
        this.changed = true;
    }

    /**
     * 客户变更需求，如：添加酒水
     * @param newContent
     * @param i
     * @throws InterruptedException
     */
    public synchronized void change(String newContent, int i) throws InterruptedException {
        synchronized (MODITOR) {
            if (changed) {
                new WaiterThread(this, i).start();
                MODITOR.wait();
            }
            this.content = newContent;
            this.changed = true;
        }

    }

    /**
     * 服务员巡视，或者客户主动叫的服务员
     * @throws IOException
     */
    public void save() throws IOException {
        synchronized (MODITOR) {
            if (!changed) {
                return;
            }
            doSave();
            this.changed = false;
            MODITOR.notifyAll();
        }
    }


    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + " calls do save,content=" + content);
        try (Writer writer = new FileWriter(fileName, true)) {
            writer.write(content);
            writer.write("\n");
            writer.flush();
        }
    }
}