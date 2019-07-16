package com.ddl.guava.io;

import com.google.common.io.ByteSource;
import com.google.common.io.Closer;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class CloserTest {

    @Test
    public void testCloser() throws IOException {
        ByteSource byteSource = Files.asByteSource(new File("D:\\ddllearn\\gitReposit\\ddlall\\ddl-learn\\src\\test\\resources\\io\\files.PNG"));
        Closer closer = Closer.create();
        try {
            InputStream inputStream = closer.register(byteSource.openStream());
        } catch (Throwable e) {
            throw closer.rethrow(e);
        } finally {
            closer.close();
        }
    }

    @Test
    public void testTryCatchFinally() {
        try {
            System.out.println("work area.");
            throw new IllegalArgumentException();
        } catch (Exception e) {
            System.out.println("exception area");
            throw new RuntimeException("1");
        } finally {
            System.out.println("finally area");
            //finallly 抛出的异常会抑制catch里抛出的异常
            //throw new RuntimeException("2");
        }
    }

    @Test
    public void testTryCatch() {
        Throwable t = null;
        try {
            throw new RuntimeException("1");
        } catch (Exception e) {
            t = e;
            throw e;
        } finally {
            try {
                //close出现了异常
                throw new RuntimeException("2");
            } catch (Exception e) {
                t.addSuppressed(e);
            }
        }
    }
}
