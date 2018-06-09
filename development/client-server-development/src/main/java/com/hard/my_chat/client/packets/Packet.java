package com.hard.my_chat.client.packets;

import java.io.DataOutputStream;

public interface Packet {
    void writeId(DataOutputStream dataOutputStream);

    void write(DataOutputStream dataOutputStream);
}
