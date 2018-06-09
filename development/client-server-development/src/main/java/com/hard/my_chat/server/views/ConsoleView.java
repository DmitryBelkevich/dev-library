package com.hard.my_chat.server.views;

import com.hard.my_chat.server.Server;

import java.util.Scanner;

public class ConsoleView extends View {
    public ConsoleView(Server server) {
        super(server);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        new Thread(()->{
            while (scanner.hasNextLine()) {
                sendMessage(scanner.nextLine());
            }
        }).start();
    }

    @Override
    public void getMessage(String str) {
        System.out.println(str);
    }

    @Override
    public void sendMessage(String str) {
        server.notifyAllViews(str);
    }
}
