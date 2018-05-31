package com.hard._01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.LinkedList;

public class Server {
    private int port;

    private ServerSocket serverSocket;

    private Collection<ClientThread> clientThreads;

    public Server() {
        port = 9999;
        clientThreads = new LinkedList<>();
    }

    public void run() {
        init();

        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ClientThread clientThread = new ClientThread(socket);
            clientThreads.add(clientThread);
            new Thread(clientThread).start();

            System.out.println(clientThreads.size());
        }
    }

    private void init() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        for (ClientThread clientThread : clientThreads)
            clientThread.stop();

        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Observable
     *
     * - Collection<Clients>
     * + add
     * + remove
     * + notifyAll
     */
}

