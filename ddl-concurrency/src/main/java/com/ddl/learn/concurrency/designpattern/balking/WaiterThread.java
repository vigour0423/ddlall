package com.ddl.learn.concurrency.designpattern.balking;

import java.io.IOException;

/**
 * 相当于巡视的服务生
 */
public class WaiterThread extends Thread {

    private final BalkingData balkingData;

    public WaiterThread(BalkingData balkingData,int i) {
        super("Waiter:"+i);
        this.balkingData = balkingData;
    }

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            try {
                balkingData.save();
                Thread.sleep(1_000L);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}