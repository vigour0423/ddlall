package com.ddl.learn;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * description: 该文件说明
 * @author liuddl
 * @version 1.0
 * @date 2019-01-22 10:26:15
 */
public class Serialize implements Serializable {
    private static final long serialVersionUID = -5001544756698020698L;

    public int num = 1390;

    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream("d:/serialize.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Serialize serialize = new Serialize();
            oos.writeObject(serialize);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
