package com.hard.byteArray.output;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        OutputStream outputStream = new ByteArrayOutputStream();

        String str = "Hello World" + "\n"
                + "Hello World"
                ;
        byte[] bytes = str.getBytes();

        try {
            outputStream.write(bytes);

            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(outputStream);
    }
}
