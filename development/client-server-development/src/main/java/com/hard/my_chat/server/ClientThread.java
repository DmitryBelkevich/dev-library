package com.hard.my_chat.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientThread implements Runnable {
    private InputStream inputStream;
    private OutputStream outputStream;

    private Socket socket;

    private boolean launched;

    public ClientThread(Socket socket) {
        this.socket = socket;

        launched = true;
    }

    @Override
    public void run() {
        initStreams();

        while (launched) {

        }
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
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
