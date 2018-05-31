package com.hard._01;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientThread implements Runnable {
    private Socket socket;

    private InputStream inputStream;
    private OutputStream outputStream;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        initStreams();

        while (true) {
            byte[] bytes = read();
            write(bytes);
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
}
