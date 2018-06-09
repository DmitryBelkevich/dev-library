package com.hard.my_chat.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientThread implements Runnable {
    private InputStream inputStream;
    private OutputStream outputStream;

    private Socket socket;

    private volatile boolean launched;

    public ClientThread(Socket socket) {
        this.socket = socket;

        launched = true;
    }

    public boolean isLaunched() {
        return launched;
    }

    public void setLaunched(boolean launched) {
        this.launched = launched;
    }

    @Override
    public void run() {
        initStreams();

        while (launched) {

        }

        stop();
    }

    public void initStreams() {
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
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
