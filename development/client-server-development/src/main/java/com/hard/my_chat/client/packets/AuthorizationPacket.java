package com.hard.my_chat.client.packets;

import java.io.DataOutputStream;
import java.io.IOException;

public class AuthorizationPacket implements Packet {
    private String username;

    public AuthorizationPacket(String username) {
        this.username = username;
    }

    @Override
    public void writeId(DataOutputStream dataOutputStream) {
        try {
            dataOutputStream.writeInt(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(DataOutputStream dataOutputStream) {
        try {
            dataOutputStream.writeUTF(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
