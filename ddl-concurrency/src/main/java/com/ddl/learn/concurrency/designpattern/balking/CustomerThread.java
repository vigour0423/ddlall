package com.ddl.learn.concurrency.designpattern.balking;

import java.util.Random;

/**
 * 相当于酒店的客户，不断的举手示意寻求服务生服务
 */
public class CustomerThread extends Thread {

    private final BalkingData balkingData;


    private final Random random = new Random(System.currentTimeMillis());

    public CustomerThread(BalkingData balkingData) {
        super("Customer");
        this.balkingData = balkingData;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                System.out.println("第几次："+i);
                balkingData.change("No." + i,i);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}