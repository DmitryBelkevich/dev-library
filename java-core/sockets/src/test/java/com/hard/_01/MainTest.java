package com.hard._01;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainTest {
    @Test
    public void test() {
        Server server = new Server();

        new Thread(() -> {
            server.run();
        }).start();

        Client client = new Client();
        client.run();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        byte[] expected = ("Hello World" + Constants.CRLF).getBytes();
        byte[] actual = Constants.actual;
        byte[] actual2 = Constants.actual2;

        Assert.assertTrue(java.util.Arrays.equals(expected, actual));
        Assert.assertTrue(java.util.Arrays.equals(expected, actual2));
    }
}

