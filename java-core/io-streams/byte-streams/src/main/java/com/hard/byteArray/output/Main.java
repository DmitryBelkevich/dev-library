package com.hard.byteArray.output;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String str = "Hello World" + "\n"
                + "Hello World";
        byte[] bytes = str.getBytes();

        // write to byte[]

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            byteArrayOutputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (byteArrayOutputStream != null)
                    byteArrayOutputStream.flush();

                if (byteArrayOutputStream != null)
                    byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(byteArrayOutputStream.toString());
        byte[] bytes2 = byteArrayOutputStream.toByteArray();
    }
}
