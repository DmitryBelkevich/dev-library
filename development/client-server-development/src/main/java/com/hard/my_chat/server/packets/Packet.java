package com.hard.my_chat.server.packets;

import java.io.DataInputStream;

public interface Packet {
    void read(DataInputStream dataInputStream);

    void execute();
}
