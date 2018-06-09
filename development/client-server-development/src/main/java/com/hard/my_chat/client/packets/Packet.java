package com.hard.my_chat.client.packets;

import java.io.DataOutputStream;

public interface Packet {
    void write(DataOutputStream dataOutputStream);
}
