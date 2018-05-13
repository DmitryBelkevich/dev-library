package com.hard;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();

        server.setClient(new Client());

        server.print();
    }
}

class Client {
    public void callback(String str) {
        System.out.println(str);
    }
}

class Server {
    private Client client;

    public void setClient(Client client) {
        this.client = client;
    }

    public void print() {
        client.callback("Hello World");
    }
}
