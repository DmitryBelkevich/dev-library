package com.hard.my_chat.server.packets;

import java.io.DataInputStream;
import java.io.IOException;

public class MessagePacket implements Packet {
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
        System.out.println("[...]: " + text);
    }
}
