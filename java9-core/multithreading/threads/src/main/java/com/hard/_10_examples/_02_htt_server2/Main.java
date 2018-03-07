package com.hard._10_examples._02_htt_server2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws Throwable {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket socket = serverSocket.accept();
            System.err.println("Client accepted");

            new Thread(new SocketProcessor(socket)).start();
        }
    }
}

class SocketProcessor implements Runnable {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public SocketProcessor(Socket socket) throws Throwable {
        this.socket = socket;
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();
    }

    public void run() {
        try {
            readInputHeaders();

            String responseBody = "<html><body><h1>Hello World</h1></body></html>";
            String responseHeaders =
                    "HTTP/1.1 200 OK" + "\r\n"
                            + "Server: YarServer/2009-09-09" + "\r\n"
                            + "Content-Type: text/html" + "\r\n"
                            + "Content-Length: " + responseBody.length() + "\r\n"
                            + "Connection: close" + "\r\n"
                            + "\r\n";

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

        while (true) {
            String socketLine = bufferedReaderSocket.readLine();
            if (socketLine == null || socketLine.trim().length() == 0)
                break;
        }
    }
}
