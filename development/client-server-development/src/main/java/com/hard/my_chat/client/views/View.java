package com.hard.my_chat.client.views;

public interface View {
    void run();

    void getMessage(String str);

    void sendMessage(String str);
}
