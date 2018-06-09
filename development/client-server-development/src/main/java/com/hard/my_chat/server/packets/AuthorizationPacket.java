package com.hard.my_chat.server.packets;

import java.io.DataInputStream;
import java.io.IOException;

public class AuthorizationPacket implements Packet {
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
        System.out.println("has joined: " + username);
    }
}
