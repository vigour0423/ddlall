package com.ddl.learn.jvm.classloader.encrypt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public final class EncryptUtils {

    public static final byte ENCRYPT_FACTOR = (byte) 0xff;

    private EncryptUtils() {
        //empty
    }

    public static void doEncrypt(String source, String target) {
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(target)) {
            int data;
            while ((data = fis.read()) != -1) {
                fos.write(data ^ ENCRYPT_FACTOR);
            }
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        doEncrypt(
                "D:\\ddllearn\\classloader3\\com\\ddl\\learn\\concurrency\\classloader\\customloader\\MyObject.class",
                "D:\\ddllearn\\classloader3\\com\\ddl\\learn\\concurrency\\classloader\\customloader\\MyObjectA.class");
    }
}