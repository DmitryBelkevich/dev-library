package com.hard;

public class Main {
    public static void main(String[] args) {
        try {
            throw new RuntimeException();
        } finally {
            System.out.println("finally");
        }
    }
}
