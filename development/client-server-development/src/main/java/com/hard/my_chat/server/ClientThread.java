package com.hard.my_chat.server;

import java.io.DataInputStream;
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
            if (socket.isClosed())
                launched = false;

            try {
                if (inputStream.available() > 0) {
                    read();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        int id = 0;
        try {
            id = dataInputStream.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Packet packet = null;

        switch (id) {
            case 1:
                packet = new AuthorizationPacket(null);
                break;
            case 2:
                packet = new MessagePacket(null);
                break;
        }

        packet.read(dataInputStream);
    }
}

interface Packet {
    void read(DataInputStream dataInputStream);

    void execute();
}

class AuthorizationPacket implements Packet {
    private String username;

    public AuthorizationPacket(String username) {
        this.username = username;
    }

    @Override
    public void read(DataInputStream dataInputStream) {
        try {
            username = dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute() {

    }
}

class MessagePacket implements Packet {
    private String text;

    public MessagePacket(String text) {
        this.text = text;
    }

    @Override
    public void read(DataInputStream dataInputStream) {
        try {
            text = dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute() {

    }
}
