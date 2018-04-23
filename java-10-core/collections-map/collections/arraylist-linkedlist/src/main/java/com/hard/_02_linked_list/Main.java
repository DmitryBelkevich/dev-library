package com.hard._02_linked_list;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> collection = new LinkedList<>();

        // 1 реализация на основе двухсвязного списка

        /**
         * минусы:
         * 1) медленный доступ к элементу списка. Линейно зависит от количества элементов после позиции вставки
         * get(int index) -> O(n)
         */

        /**
         * плюсы:
         * 1) быстрая вставка в начало, середину, конец списка
         * add(E e) -> O(1)
         *
         * однако, вставка в середину:
         * поиск O(n)
         *
         * 2) быстрое удаление из начала, середины, конца списка
         * remove(int index) -> O(1)
         *
         * однако, удаление из середины:
         * поиск O(n)
         */

        /**
         * использование:
         * частая вставка, удаление
         * вставка в конец списка
         */

        final int n = 1 * 100 * 1000;

        // получение
//        for (int i = 0; i < n; i++) // 4991 ms
//            collection.get(i);

        // вставка в конец
//        for (int i = 0; i < n; i++) // 5 ms
//            collection.add(i);

        // вставка в начало, середину
        for (int i = 0; i < n; i++) // 5 ms
            collection.add(0);

        // удаление из конца
//        for (int i = n - 1; i >= 0; i--) // 3 ms
//            collection.remove(i);

        // удаление из начала, середины
//        for (int i = 0; i < n; i++) // 3 ms
//            collection.remove(0);

        long start = System.currentTimeMillis();


        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        System.out.println(timeConsumedMillis + " ms");
    }
}
