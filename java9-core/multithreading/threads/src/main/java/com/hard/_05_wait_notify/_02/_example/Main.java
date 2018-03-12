package com.hard._05_wait_notify._02._example;

public class Main {
    public static void main(String[] args) {
        Object sync = new Object();

        Client client1 = new Client(sync);
        client1.start();

        Client client2 = new Client(sync);
        client2.start();

        Server server = new Server(sync);
        server.start();
    }
}

class Client extends Thread {
    private Object sync;

    public Client(Object sync) {
        this.sync = sync;
    }

    @Override
    public void run() {
        synchronized (sync) {
            System.out.println(this + " client waiting messages");

            try {
                sync.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(this + " client end waiting messages");
        }
    }
}

class Server extends Thread {
    private Object sync;

    public Server(Object sync) {
        this.sync = sync;
    }

    @Override
    public void run() {
        synchronized (sync) {
            System.out.println(this + " server doesn't have messages");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(this + " server has messages");
            sync.notifyAll();
        }
    }
}
