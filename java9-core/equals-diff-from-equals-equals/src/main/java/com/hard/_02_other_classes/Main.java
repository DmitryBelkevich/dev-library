package com.hard._02_other_classes;

public class Main {
    public static void main(String[] args) {
        /*
         * equals() обозначает отношение эквивалентности объектов
         */

        boolean result;

        /*
         * в классах Integer, Long, String, ..., метод equals() переопределён.
         * Сравнивает объекты по значению
         */

        Integer num1;
        Integer num2;

        num1 = new Integer(5); // объект 1
        num2 = new Integer(5); // объект 2

        result = num1 == num2;
        System.out.println(result);  // false - ссылка на разные объекты

        result = num1.equals(num2);
        System.out.println(result);  // true - значения одинаковые

        System.out.println("------------");

        String str1;
        String str2;

        str1 = new String("Hello World"); // объект 1
        str2 = new String("Hello World"); // объект 2

        result = str1 == str2;
        System.out.println(result);  // false - ссылка на разные объекты

        result = str1.equals(str2);
        System.out.println(result);  // true - значения одинаковые
    }
}
