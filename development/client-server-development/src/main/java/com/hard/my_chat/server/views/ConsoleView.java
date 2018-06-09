package com.hard.my_chat.server.views;

import com.hard.my_chat.server.Server;

public class ConsoleView extends View {
    public ConsoleView(Server server) {
        super(server);
    }

    @Override
    public void run() {

    }

    @Override
    public void getMessage(String str) {
        System.out.println(str);
    }
}
