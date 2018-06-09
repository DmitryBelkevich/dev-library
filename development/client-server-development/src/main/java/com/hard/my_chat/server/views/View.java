package com.hard.my_chat.server.views;

import com.hard.my_chat.server.Server;

public abstract class View {
    protected Server server;

    public View(Server server) {
        this.server = server;
    }

    public abstract void run();

    public abstract void getMessage(String str);

    public abstract void sendMessage(String str);
}
