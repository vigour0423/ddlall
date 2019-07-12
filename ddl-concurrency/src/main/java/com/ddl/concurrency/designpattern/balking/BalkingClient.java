package com.ddl.concurrency.designpattern.balking;


import java.util.LinkedList;

public class BalkingClient {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.addFirst("===BEGIN====");
        BalkingData balkingData = new BalkingData
                ("D:\\ddllearn\\gitReposit\\ddlall\\ddl-concurrency\\src\\main\\java\\com\\ddl\\learn\\concurrency" +
                        "\\designpattern\\balking\\balking.txt", linkedList);

        new CustomerThread(balkingData).start();
        for (int i = 300; i < 600; i++) {
            new WaiterThread(balkingData, +i).start();

        }

      /*  new WaiterThread(balkingData, 203).start();
        new WaiterThread(balkingData, 204).start();
        new WaiterThread(balkingData, 205).start();*/
    }
}