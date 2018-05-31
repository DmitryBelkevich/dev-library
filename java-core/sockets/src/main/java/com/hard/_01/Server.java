package com.hard._01;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port = 9999;

    private ServerSocket serverSocket;
    private Socket socket;

    private InputStream inputStream;
    private OutputStream outputStream;

    public void run() {
        init();

        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }

        initStreams();

        byte[] bytes = read();
        Constants.actual = bytes;

        write(bytes);
    }

    private void init() {
        try {
            serverSocket = new ServerSocket(port);
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

        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Observable
     *
     * - Collection<Clients>
     * + add
     * + remove
     * + notifyAll
     */
}
