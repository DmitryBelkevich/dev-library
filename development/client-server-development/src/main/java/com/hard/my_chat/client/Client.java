package com.hard.my_chat.client;

import com.hard.my_chat.client.packets.AuthorizationPacket;
import com.hard.my_chat.client.packets.MessagePacket;
import com.hard.my_chat.client.packets.Packet;
import com.hard.my_chat.client.views.ConsoleView;
import com.hard.my_chat.client.views.FrameView;
import com.hard.my_chat.client.views.View;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private Collection<View> views;

    private InputStream inputStream;
    private OutputStream outputStream;

    private Socket socket;

    private volatile boolean launched;

    public Client() {
        views = new ArrayList<>();

        views.add(new ConsoleView(this));
        views.add(new FrameView(this));

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
        initStreams();

        write(new AuthorizationPacket(this.toString()));

        while (launched) {
            write(new MessagePacket("Hello World"));
        }

        stop();
    }

    public void init() {
        for (View view : views)
            view.run();

        try {
            socket = new Socket("localhost", 9999);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initStreams() {
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void notifyAllViews(String str) {
        for (View view : views)
            view.getMessage(str);
    }

    public void write(Packet packet) {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        packet.writeId(dataOutputStream);
        packet.write(dataOutputStream);
    }
}
