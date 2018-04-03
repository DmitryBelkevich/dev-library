package com.hard.file.output;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        OutputStream outputStream = null;

        File file = new File("c:/000.txt");

        try {
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        byte[] bytes = {
                'H',
                'e',
                'l',
                'l',
                'o',
                ' ',
                'W',
                'o',
                'r',
                'l',
                'd',
        };

        try {
            outputStream.write(bytes);

            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
