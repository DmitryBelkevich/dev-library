package com.hard;

public class Main {
    public static void main(String[] args) {
        Integer num1;
        Integer num2;
        boolean result;

        num1 = 5; // объект 1
        num2 = 5; // объект 1

        result = num1 == num2;
        System.out.println(result);  // true - ссылка на один объект

        num2 = new Integer(5); // объект 2

        result = num1 == num2;
        System.out.println(result);  // false - ссылка на разные объекты

        System.out.println("------------");

        String str1;
        String str2;

        str1 = "Hello World"; // объект 1
        str2 = "Hello World"; // объект 1

        result = str1 == str2;
        System.out.println(result);  // true - ссылка на один объект

        str2 = new String("Hello World"); // объект 2

        result = str1 == str2;
        System.out.println(result);  // false - ссылка на разные объекты
    }
}
