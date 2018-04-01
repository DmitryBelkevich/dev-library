package com.hard._1_simple;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        TextChat textChat = new TextChat();

        User admin = new Admin(textChat);
        User user1 = new SimpleUser(textChat);
        User user2 = new SimpleUser(textChat);

        textChat.setAdmin(admin);
        textChat.addUser(user1);
        textChat.addUser(user2);

        admin.sendMessage("Admin has connected to chat");
        user1.sendMessage("Hello, i'm user1");
        user2.sendMessage("Hello, i'm user2");
    }
}

/**
 * Client
 */

interface User {
    void sendMessage(String message);    // broadcastEvent

    void getMessage(String message);        // handleEvent
}

/**
 * Concrete Client
 */

class Admin implements User {
    private Chat chat;

    public Admin(Chat chat) {
        this.chat = chat;
    }

    @Override
    public void sendMessage(String message) {
        chat.sendMessage(message, this);
    }

    @Override
    public void getMessage(String message) {
        System.out.println("Администратор получает сообщение: " + message);
    }
}

class SimpleUser implements User {
    private Chat chat;

    public SimpleUser(Chat chat) {
        this.chat = chat;
    }

    @Override
    public void sendMessage(String message) {
        chat.sendMessage(message, this);
    }

    @Override
    public void getMessage(String message) {
        System.out.println("Пользователь получает сообщение: " + message);
    }
}

/**
 * Mediator
 */

interface Chat {
    void sendMessage(String message, User user);    // broadcastEvent
}

/**
 * Concrete Mediator
 */

class TextChat implements Chat {
    private User admin;
    private Collection<User> users = new ArrayList<>();

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void sendMessage(String message, User user) {
        admin.getMessage(message);

        for (User u : users)
            u.getMessage(message);
    }
}
