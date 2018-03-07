package com.hard._10_examples;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();

        server.run(8080);

        while (true) {
            Socket socket = server.listen();

            ClientThread clientThread = new ClientThread(socket);
            server.addClient(clientThread);
            clientThread.start();
        }
    }
}

class ClientThread extends Thread {
    private Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                System.out.println(str);
            }

            System.out.println("END");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Server {
    private ServerSocket serverSocket;
    private Collection<ClientThread> clientThreads = new LinkedList<>();

    public void run(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket listen() {
        Socket socket = null;

        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return socket;
    }

    public void addClient(ClientThread clientThread) {
        clientThreads.add(clientThread);
    }
}
