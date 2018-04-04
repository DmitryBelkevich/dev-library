package com.hard.console.input;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = null;

        inputStream = System.in;

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String str = null;
        try {
            str = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(str);
    }
}
