package com.hard.file.output;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Writer writer = null;

        File file = new File("c:/000.txt");

        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String str = "Hello World" + "\n"
                + "Hello World"
                ;
        char[] chars = str.toCharArray();

        try {
            writer.write(chars);

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
