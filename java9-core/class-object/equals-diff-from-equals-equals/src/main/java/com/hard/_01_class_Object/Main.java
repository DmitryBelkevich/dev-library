package com.hard._01_class_Object;

public class Main {
    public static void main(String[] args) {
        /*
         * equals() обозначает отношение эквивалентности объектов
         */

        Object obj1;
        Object obj2;
        boolean result;

        /*
         * в классе Object отличий нет
         * == сравнивает ссылки на объекты
         * equals() сравнивает ссылки на объекты
         */

        obj1 = new Object();
        obj2 = new Object();

        result = obj1 == obj2;
        System.out.println(result);  // false - ссылки на разные объекты

        result = obj1.equals(obj2);
        System.out.println(result);  // false - ссылки на разные объекты

        System.out.println("------------");

        obj1 = new Object();
        obj2 = obj1;

        result = obj1 == obj2;
        System.out.println(result);  // true - ссылка на один и тот же объект

        result = obj1.equals(obj2);
        System.out.println(result);  // true - ссылка на один и тот же объект

        System.out.println("------------");

        // difference 2

        obj1 = null;
        obj2 = new Object();

//        result = obj1.equals(obj2); // NPE
        result = null == obj2;      // false
        System.out.println(result);

        // difference 3

        obj1 = null;
        obj2 = null;

//        result = obj1.equals(obj2); // NPE
        result = obj1 == obj2; // true
        System.out.println(result);
    }
}
