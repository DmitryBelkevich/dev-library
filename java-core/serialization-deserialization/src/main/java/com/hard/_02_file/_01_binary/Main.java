package com.hard._02_file._01_binary;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Entity entity = new Entity(1, "Hello World");

        // encode (serialization) from Object to byte[]

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("c:/000.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            objectOutputStream.writeObject(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.flush();
                    objectOutputStream.close();
                }

                if (fileOutputStream != null) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // decode (deserialization) from byte[] to Object

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("c:/000.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(fileInputStream);
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
        } finally {
            try {
                if (objectInputStream != null)
                    objectInputStream.close();

                if (fileOutputStream != null)
                    fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(entity2);
    }
}
