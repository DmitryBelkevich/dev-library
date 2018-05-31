package com.hard._01;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    private String host = "localhost";
    private int port = 9999;

    private Socket socket;

    private InputStream inputStream;
    private OutputStream outputStream;

    public void run() {
        init();
        initStreams();

        String str = "Hello World" + Constants.CRLF;
        byte[] bytes = str.getBytes();

        write(bytes);
        byte[] bytes2 = read();

        System.out.println(new String(bytes2));

        Constants.actual2 = bytes2;
    }

    private void init() {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initStreams() {
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] read() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] buffer;

            while (true) {
                buffer = new byte[4096];

                int read = inputStream.read(buffer);

                if (read == -1)
                    break;

                byteArrayOutputStream.write(buffer, 0, read);

                int indexOf = byteArrayOutputStream.toString().indexOf(new String(Constants.CRLF.getBytes()));
                if (indexOf != -1) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] bytes = byteArrayOutputStream.toByteArray();

        return bytes;
    }

    private void write(byte[] bytes) {
        try {
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * interface Observer
     *
     * Client impl Observer
     * - Server
     * + update(String str) // getMessage
     */
}
