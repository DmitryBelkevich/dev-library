package com.hard;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();

        server.run();
    }
}

class Server {
    private static final String CRLF = "\r\n";
    private int port = 8080;
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public void run() {
        init();

        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }

        initStreams();

        /**
         * request
         */
        String requestHeaders = readRequestHeaders();

        System.out.println(requestHeaders);

        /**
         * response
         */
        String responseBody = generateResponseBody();

        String responseHeaders = generateResponseHeaders(responseBody);

        String response = responseHeaders + responseBody;

        writeResponse(response.getBytes());
    }

    public void init() {
        try {
            serverSocket = new ServerSocket(port);
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

    private String readRequestHeaders() {
        Scanner scanner = new Scanner(inputStream).useDelimiter(CRLF + CRLF);

        String requestHeaders = scanner.next();

        return requestHeaders;
    }

    private String generateResponseBody() {
        String responseBody = "<html><body><h1>Hello World</h1></body></html>";

        return responseBody;
    }

    private String generateResponseHeaders(String responseBody) {
        String responseHeaders =
                "HTTP/1.1 200 OK" + CRLF
                        + "Server: YarServer/2009-09-09" + CRLF
                        + "Content-Type: text/html" + CRLF
                        + "Content-Length: " + responseBody.length() + CRLF
                        + "Connection: close" + CRLF
                        + CRLF;

        return responseHeaders;
    }

    private void writeResponse(byte[] response) {
        try {
            outputStream.write(response);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
