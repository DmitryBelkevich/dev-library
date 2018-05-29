package com.hard.string._01_byte_array;

import com.hard.string._01_byte_array.Entity;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Entity entity = new Entity(1, "Hello World");

        // encode

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            objectOutputStream.writeObject(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            objectOutputStream.flush();
            byteArrayOutputStream.flush();
            objectOutputStream.close();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] bytes = byteArrayOutputStream.toByteArray();

        // decode

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Entity entity2 = null;
        try {
            entity2 = (Entity) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            objectInputStream.close();
            byteArrayInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(entity2);
    }
}
