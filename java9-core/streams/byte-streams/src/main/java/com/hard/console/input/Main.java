package com.hard.console.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = null;

        inputStream = System.in;

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String str = null;
        try {
            while ((str = bufferedReader.readLine()) != null) {
                if (str.equalsIgnoreCase(""))
                    break;

                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();

                if (inputStreamReader != null)
                    inputStreamReader.close();

                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
