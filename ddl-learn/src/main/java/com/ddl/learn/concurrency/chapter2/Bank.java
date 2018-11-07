package com.ddl.learn.concurrency.chapter2;

public class Bank {
    public static void main(String[] args) {

        TicketWindow ticketWindow1 = new TicketWindow("一号出号机");
        ticketWindow1.start();

        TicketWindow ticketWindow2 = new TicketWindow("二号出号机");
        ticketWindow2.start();

        TicketWindow ticketWindow3 = new TicketWindow("三号出号机");
        ticketWindow3.start();

        TicketWindow ticketWindow4 = new TicketWindow("四号出号机");
        ticketWindow4.start();

    }
}
