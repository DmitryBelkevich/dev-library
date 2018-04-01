package com.hard._2_advanced;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        TextChat textChat = new TextChat();

        User admin = new Admin(textChat, "admin");
        User user1 = new SimpleUser(textChat, "user1");
        User user2 = new SimpleUser(textChat, "user2");
        User user3 = new SimpleUser(textChat, "user3");

        user2.setEnable(false);

        textChat.setAdmin(admin);
        textChat.addUser(user1);
        textChat.addUser(user2);
        textChat.addUser(user3);

        admin.sendMessage("Admin has connected to chat");
        user1.sendMessage("Hello, i'm user1");
    }
}

/**
 * Client
 */

abstract class User {
    private Chat chat;
    private String name;
    private boolean enable = true;

    public User(Chat chat, String name) {
        this.chat = chat;
        this.name = name;
    }

    public void sendMessage(String message) {
        chat.sendMessage(message, this);
    }

    public abstract void getMessage(String message);

    public String getName() {
        return name;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isEnable() {
        return enable;
    }

    @Override
    public String toString() {
        return "User ["
                + "name=" + name
                + "isEnable=" + enable
                + "]";
    }
}

/**
 * Concrete Client
 */

class Admin extends User {
    public Admin(Chat chat, String name) {
        super(chat, name);
    }

    @Override
    public void getMessage(String message) {
        System.out.println("Администратор " + getName() + " получает сообщение: " + message);
    }
}

class SimpleUser extends User {
    public SimpleUser(Chat chat, String name) {
        super(chat, name);
    }

    @Override
    public void getMessage(String message) {
        System.out.println("Пользователь " + getName() + " получает сообщение: " + message);
    }
}

/**
 * Mediator
 */

interface Chat {
    void sendMessage(String message, User user);
}

/**
 * Concrete Mediator
 */

class TextChat implements Chat {
    private User admin;
    private Collection<User> users = new ArrayList<>();

    public void setAdmin(User admin) {
        if (admin != null && admin instanceof Admin)
            this.admin = admin;
        else
            throw new RuntimeException("Не хватает прав");
    }

    public void addUser(User user) {
        if (admin == null)
            throw new RuntimeException("В чате нет админа");

        if (user instanceof SimpleUser)
            users.add(user);
        else
            throw new RuntimeException("Админ не может входить в другой чат");
    }

    @Override
    public void sendMessage(String message, User user) {
        if (user instanceof Admin)
            for (User u : users)
                u.getMessage("[" + user.getName() + "]: " + message);
        else if (user instanceof SimpleUser) {
            if (admin.isEnable())
                admin.getMessage("[" + user.getName() + "]: " + message);

            for (User u : users)
                if (u != user && u.isEnable())
                    u.getMessage("[" + user.getName() + "]: " + message);
        }
    }
}
