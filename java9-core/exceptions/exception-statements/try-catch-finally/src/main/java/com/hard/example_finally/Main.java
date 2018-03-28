package com.hard.example_finally;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Reader reader = new Reader();

        try {
            reader.read();
        } catch (NotReadingException e) {
            e.printStackTrace();
            throw new RuntimeException("exception in catch");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * exceptions
 */

class NotReadingException extends IOException {
    public NotReadingException() {
    }

    public NotReadingException(String message) {
        super(message);
    }
}

/**
 * class
 */

class Reader {
    public void read() throws NotReadingException {
        System.out.println("reading");
        throw new NotReadingException("entity isn't reading");
    }

    public void close() throws IOException {
        System.out.println("closing");
    }
}
