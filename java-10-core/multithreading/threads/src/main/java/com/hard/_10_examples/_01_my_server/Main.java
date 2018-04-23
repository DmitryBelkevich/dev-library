package com.hard._10_examples._01_my_server;

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
            readInputHeaders();

            String responseBody = "<html><body><h1>Hello World</h1></body></html>";
            writeResponse(responseBody);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private void readInputHeaders() throws Throwable {
        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }

        System.out.println("END");
    }

    private void readInputHeaders2() throws Throwable {
        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String socketLine = null;
        while (true) {
            socketLine = bufferedReader.readLine();

            if (socketLine == null || socketLine.trim().length() == 0)
                break;
        }
        System.out.println(socketLine);
    }

    private void writeResponse(String responseBody) {
        String responseHeaders =
                "HTTP/1.1 200 OK" + Constants.CRLF
                        + "Server: YarServer/2009-09-09" + Constants.CRLF
                        + "Content-Type: text/html" + Constants.CRLF
                        + "Content-Length: " + responseBody.length() + Constants.CRLF
                        + "Connection: close" + Constants.CRLF
                        + Constants.CRLF;

        String response = responseHeaders + responseBody;
        byte[] byteResponse = response.getBytes();

        OutputStream outputStream = null;
        try {
            outputStream = socket.getOutputStream();
            outputStream.write(byteResponse);
            outputStream.flush();
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

class Constants {
    public static final String CRLF = "\r\n";
}
