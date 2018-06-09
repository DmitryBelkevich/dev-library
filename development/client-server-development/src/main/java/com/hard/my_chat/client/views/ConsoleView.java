package com.hard.my_chat.client.views;

import com.hard.my_chat.client.Client;

import java.util.Scanner;

public class ConsoleView extends View {
    public ConsoleView(Client client) {
        super(client);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            sendMessage(scanner.nextLine());
        }
    }

    @Override
    public void getMessage(String str) {
        System.out.println(str);
    }

    @Override
    public void sendMessage(String str) {
        client.notifyAllViews(str);
    }
}
