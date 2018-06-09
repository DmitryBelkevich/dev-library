package com.hard.my_chat.client.views;

import com.hard.my_chat.client.Client;

public abstract class View {
    protected Client client;

    public View(Client client) {
        this.client = client;
    }

    public abstract void run();

    public abstract void getMessage(String str);

    public abstract void sendMessage(String str);
}
