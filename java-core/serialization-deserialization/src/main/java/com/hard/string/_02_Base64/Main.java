package com.hard.string._02_Base64;

import com.hard.string._01_byte_array.Entity;

import java.io.*;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        Entity entity = new Entity(1, "Hello World");

        // encode (serialization) from Object to String (base64)

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
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.flush();
                    objectOutputStream.close();
                }

                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        byte[] bytes = byteArrayOutputStream.toByteArray();

        Base64.Encoder encoder = Base64.getEncoder();
        String str = encoder.encodeToString(bytes);
        System.out.println(str);

        // decode (deserialization) from String (base64) to Object

        byte[] bytes2 = Base64.getDecoder().decode(str);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes2);
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
        } finally {
            try {
                if (objectInputStream != null)
                    objectInputStream.close();

                if (byteArrayInputStream != null)
                    byteArrayInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(entity2);
    }
}
