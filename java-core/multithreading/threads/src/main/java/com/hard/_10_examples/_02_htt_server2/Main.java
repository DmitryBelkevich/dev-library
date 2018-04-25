package com.hard._10_examples._02_htt_server2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws Throwable {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);

        Collection<ClientThread> clientThreads = new LinkedList<>();

        while (true) {
            Socket socket = serverSocket.accept();
            System.err.println("Client accepted");

            ClientThread clientThread = new ClientThread(socket);
            clientThreads.add(clientThread);

            clientThread.start();
        }
    }
}

class ClientThread extends Thread {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public ClientThread(Socket socket) throws Throwable {
        this.socket = socket;
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();
    }

    public void run() {
        try {
            readInputHeaders();

            String responseBody = "<html>" +
                    "<body>" +
                    "<h1>Hello World</h1>" +
                    "</body>" +
                    "</html>";
            String responseHeaders =
                    "HTTP/1.1 200 OK" + Constants.CRLF
                            + "Server: YarServer/2009-09-09" + Constants.CRLF
                            + "Content-Type: text/html" + Constants.CRLF
                            + "Content-Length: " + responseBody.length() + Constants.CRLF
                            + "Connection: close" + Constants.CRLF
                            + Constants.CRLF;

            String response = responseHeaders + responseBody;

            writeResponse(response);
        } catch (Throwable t) {
            /*do nothing*/
        } finally {
            try {
                socket.close();
            } catch (Throwable t) {
                /*do nothing*/
            }
        }

        System.err.println("Client processing finished");
    }

    private void writeResponse(String response) throws Throwable {
        byte[] responseBytes = response.getBytes();

        outputStream.write(responseBytes);

        outputStream.flush();
    }

    private void readInputHeaders() throws Throwable {
        BufferedReader bufferedReaderSocket = new BufferedReader(new InputStreamReader(inputStream));

        String socketLine = null;
        while (true) {
            socketLine = bufferedReaderSocket.readLine();
            if (socketLine == null || socketLine.trim().length() == 0)
                break;
        }
        System.out.println(socketLine);
    }
}

class Constants {
    public static final String CRLF = "\r\n";
}
