package com.hard.byteArray.input;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String s = "Hello World" + "\n"
                + "Hello World"
                ;
        byte[] bytes = s.getBytes();

        InputStream inputStream = new ByteArrayInputStream(bytes);

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String str = null;
        try {
            while ((str = bufferedReader.readLine()) != null) {
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
