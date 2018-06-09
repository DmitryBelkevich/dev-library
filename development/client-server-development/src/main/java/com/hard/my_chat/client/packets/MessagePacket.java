package com.hard.my_chat.client.packets;

import java.io.DataOutputStream;
import java.io.IOException;

public class MessagePacket implements Packet {
    private String text;

    public MessagePacket(String text) {
        this.text = text;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) {
        try {
            dataOutputStream.writeUTF(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
