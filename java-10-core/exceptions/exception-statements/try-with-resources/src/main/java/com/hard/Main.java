package com.hard;

import java.io.Closeable;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try (Reader reader = new Reader()) {
            reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("after reader");
    }
}

/**
 * exceptions
 */

class NotFoundException extends IOException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}

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

class Reader implements Closeable {
    public void read() throws NotFoundException, NotReadingException {
        System.out.println("reader has opened");

        if (true)
            throw new NotFoundException("entity isn't found");

        System.out.println("entity has founded");

        if (true)
            throw new NotReadingException("entity isn't reading");

        System.out.println("entity has read");
    }

    @Override
    public void close() throws IOException {
        if (true)
            throw new IOException("reader isn't closing");

        System.out.println("reader has closed");
    }
}
