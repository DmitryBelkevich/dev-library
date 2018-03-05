package com.hard;

public class Main {
    public static void main(String[] args) {
        /*
         * Эквивалентным называется отношение, которое является:
         * симметричным, транзитивным и рефлексивным
         */

        /*
         * Рефлексивность: для любого ненулевого x, x.equals(x) вернет true
         */

        String str1;
        String str2;
        boolean result;

        str1 = new String("Hello World");
        str2 = new String("Hello World");

        result = str1.equals(str2);
        System.out.println(result);  // true

        /*
         * Транзитивность: для любого ненулевого x, y и z,
         * если x.equals(y) и y.equals(z) вернет true, тогда и x.equals(z) вернет true;
         */

        String str3;
        str3 = new String("Hello World");

        result = str1.equals(str3);
        System.out.println(result);  // true

        /*
         * Симметричность: для любого ненулевого x и y, x.equals(y) должно вернуть true,
         * тогда и только тогда, когда y.equals(x) вернет true.
         */
    }
}
