package com.ddl.concurrency.designpattern.balking;

import java.io.IOException;

/**
 * 相当于巡视的服务生
 */
public class WaiterThread extends Thread {

    private BalkingData balkingData;

    public WaiterThread(BalkingData balkingData, int i) {
        super("Waiter:" + i);
        this.balkingData = balkingData;
    }

    @Override
    public void run() {
        while (true) {
            try {
                balkingData.save();
                Thread.sleep(1L);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}