package com.ddl.learn.concurrency.designpattern.balking;


public class BalkingClient {
    public static void main(String[] args) {
        BalkingData balkingData = new BalkingData
                ("D:\\ddllearn\\gitReposit\\ddlall\\ddl-learn\\src\\main\\java\\com\\ddl\\learn\\concurrency" +
                        "\\designpattern\\balking\\balking.txt", "===BEGIN====");

        new CustomerThread(balkingData).start();
        new WaiterThread(balkingData).start();
    }
}