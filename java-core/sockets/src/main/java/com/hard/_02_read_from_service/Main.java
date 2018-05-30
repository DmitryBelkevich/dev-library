package com.hard._02_read_from_service;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();

        client.run();
        client.stop();

        client.run();
        client.stop();
    }
}

class Client {
    private Socket socket;

    private void init() {
        socket = new Socket();
    }

    public void run() {
        init();

        read();
    }

    public void read() {
        SocketAddress socketAddress = new InetSocketAddress("india.colorado.edu", 13);
        try {
            socket.connect(socketAddress, 2000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }

    public void stop() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
