package com.hard.file.output;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String str = "Hello World" + "\n"
                + "Hello World"
                ;
        byte[] bytes = str.getBytes();

        // write to file

        File file = new File("c:/000.txt");

        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null)
                    outputStream.flush();

                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
