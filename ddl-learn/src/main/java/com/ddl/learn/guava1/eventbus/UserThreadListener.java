package com.ddl.learn.guava1.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class UserThreadListener extends Thread {
    private Socket connection;

    private EventBus channel;

    private BufferedReader in;

    private PrintWriter out;

    public UserThreadListener(Socket connection, EventBus channel) {
        this.connection = connection;
        this.channel = channel;
        try {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            out = new PrintWriter(connection.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Subscribe
    public void recieveMessage(String message) {
        if (out != null) {
            out.println(message + ":haha");
            System.out.println("recieveMessage:" + message);
        }
    }

    @Override
    public void run() {
        try {
            String input;
            while ((input = in.readLine()) != null) {
                channel.post(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //注销监听者
        channel.unregister(this);
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}