package com.hard;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

    }
}

class Server {
    private static Map<Socket, ClientThread> clientThreads = new HashMap();

    public void run() {
        while (true) {
            Socket socket = null;   // accept()
            ClientThread clientThread = new ClientThread(socket);
            clientThreads.put(socket, clientThread);
            clientThread.run();     // thread.start()
        }
    }

    public static ClientThread getClientThread(Socket socket) {
        return clientThreads.get(socket);
    }

    public static void removeClientThread(Socket socket) {
        clientThreads.remove(socket);
    }
}

class ClientThread {
    private Socket socket;
    private DataInputStream dataInputStream;

    private String username;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void run() {
        while (true) {
            readData();
        }
    }

    public void readData() {
        int id = 0;
        try {
            id = dataInputStream.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }

        OPacket packet = PacketManager.getPacket(id);
        packet.setSocket(socket);
        try {
            packet.read(dataInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        packet.handle();
    }

    public void removeClient() {
        Server.removeClientThread(socket);
    }
}

/**
 * Client
 */

class Client {
    private DataOutputStream dataOutputStream;

    public void run() {
        sendPacket(new AuthorizePacket("aaa"));
        sendPacket(new MessagePacket("aaa", "Hello"));
    }

    public void sendPacket(OPacket packet) {
        int id = packet.getId();
        try {
            dataOutputStream.writeInt(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            packet.write(dataOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Packet
 */

abstract class OPacket {
    protected Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public abstract int getId();

    public abstract void write(DataOutputStream dataOutputStream) throws IOException;   // for client

    public abstract void read(DataInputStream dataInputStream) throws IOException;      // for server

    public abstract void handle();  // for server
}

class AuthorizePacket extends OPacket {
    private String username;

    public AuthorizePacket() {
    }

    public AuthorizePacket(String username) {
        this.username = username;
    }

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(username);
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        username = dataInputStream.readUTF();
    }

    @Override
    public void handle() {
        ClientThread clientThread = Server.getClientThread(socket);
        clientThread.setUsername(username);

        System.out.println(username + " has joined");
    }
}

class MessagePacket extends OPacket {
    private String username;
    private String message;

    public MessagePacket() {
    }

    public MessagePacket(String username, String message) {
        this.username = username;
        this.message = message;
    }

    @Override
    public int getId() {
        return 2;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(message);
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        username = dataInputStream.readUTF();
        message = dataInputStream.readUTF();
    }

    @Override
    public void handle() {

    }
}

class LevelUpPacket extends OPacket {
    private String username;
    private int level;

    public LevelUpPacket() {
    }

    public LevelUpPacket(String username, int level) {
        this.username = username;
        this.level = level;
    }

    @Override
    public int getId() {
        return 3;
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(username);
        dataOutputStream.writeInt(level);
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        username = dataInputStream.readUTF();
        level = dataInputStream.readInt();
    }

    @Override
    public void handle() {

    }
}

class PacketManager {
    private static final Map<Integer, Class<? extends OPacket>> packets = new HashMap<>();

    static {
        packets.put(1, AuthorizePacket.class);
        packets.put(2, MessagePacket.class);
        packets.put(3, LevelUpPacket.class);

        // var2 - fill via reflection
        // scan package
        // check on class extends OPacket
        // create instance
        // getId
        // put to map
    }

    public static OPacket getPacket(int id) {
        Class<?> clazz = packets.get(id);
        OPacket packet = null;
        try {
            packet = (OPacket) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return packet;
    }
}
