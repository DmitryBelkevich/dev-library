package com.hard._01;

public class Main {
    public static void main(String[] args) {

    }
}

class ServerLoader {
    public static void main(String[] args) {
        Server server = new Server(9999);
        server.run();
    }
}

class ClientLoader {
    public static void main(String[] args) {
        Client client = new Client("localhost", 9999);
        client.run();
    }
}
