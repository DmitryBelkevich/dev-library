package com.hard.my_chat.server;

import com.hard.my_chat.server.views.ConsoleView;
import com.hard.my_chat.server.views.FrameView;
import com.hard.my_chat.server.views.View;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class Server {
    private Collection<View> views;

    private ServerSocket serverSocket;
    private Collection<ClientThread> clientThreads;

    private volatile boolean launched;

    public Server() {
        views = new ArrayList<>();

        views.add(new ConsoleView(this));
        views.add(new FrameView(this));

        clientThreads = new LinkedList<>();

        launched = true;
    }

    public boolean isLaunched() {
        return launched;
    }

    public void setLaunched(boolean launched) {
        this.launched = launched;
    }

    public void run() {
        init();

        while (launched) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ClientThread clientThread = new ClientThread(socket);
            clientThreads.add(clientThread);
            new Thread(clientThread).start();
        }

        stop();
    }

    public void init() {
        for (View view : views)
            view.run();

        try {
            serverSocket = new ServerSocket(9999);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        for (ClientThread clientThread : clientThreads)
            clientThread.setLaunched(false);

        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void notifyAllViews(String str) {
        for (View view : views)
            view.getMessage(str);
    }

    public int clientsSize() {
        return clientThreads.size();
    }
}
