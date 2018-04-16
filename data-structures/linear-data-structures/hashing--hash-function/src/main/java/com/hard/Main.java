package com.hard;

public class Main {
    public static void main(String[] args) {
        String str = "Hello World";

        Hashing hashing = new Hashing();
        int hashcode = hashing.hashcode(str);

        System.out.println(hashcode);
    }
}

class Hashing {
    public int hashcode(int data) {
        int size = 4;   // hash-table size

        int hashcode = data % size;

        return hashcode;
    }

    // полиномный хеш: (a1 * p^0 + a2 * p^1 + a3 * p^2 + ...) Mod 2^n
    public int hashcode(String data) {
        byte[] bytes = data.getBytes();

        int sum = 0;
        int p = 10;
        for (int i = 0; i < bytes.length; i++) {
            int x = (int) Math.pow(p, i);
            sum += bytes[i] * x;
        }

        int n = 8;
        int size = (int) Math.pow(2, n);

        int hashcode = sum % size;

        return hashcode;
    }
}
