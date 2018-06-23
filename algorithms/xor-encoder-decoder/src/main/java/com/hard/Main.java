package com.hard;

import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        String str = "Hello World";
        String key = Base64.getEncoder().encodeToString(
                ("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890").getBytes()
        );

        System.out.println(new String(Base64.getDecoder().decode(key)));

        byte[] encodedBytes = Encoder.encode(str.getBytes(), key.getBytes());
        String encoded = new String(encodedBytes);
        System.out.println(encoded);

        byte[] decodedBytes = Decoder.decode(encoded.getBytes(), key.getBytes());
        String decoded = new String(decodedBytes);
        System.out.println(decoded);
    }
}

class Encoder {
    public static byte[] encode(byte[] bytes, byte[] key) {
        byte[] result = new byte[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            result[i] = (byte) (bytes[i] ^ key[i % key.length]);
        }

        return result;
    }
}

class Decoder {
    public static byte[] decode(byte[] bytes, byte[] key) {
        byte[] result = new byte[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            result[i] = (byte) (bytes[i] ^ key[i % key.length]);
        }

        return result;
    }
}
