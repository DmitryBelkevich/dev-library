package com.hard._01_array_list;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> collection = new ArrayList<>();

        // 1 реализация на основе массива

        /**
         * плюсы:
         * 1) быстрый доступ к элементу списка
         * get(int index) -> O(1)
         *
         * 2) быстрая вставка в конец списка
         * add(E e) -> O(1)
         * однако при вставке в список, при превышении capacity создаётся новый массив
         * все элементы копируются в него
         *
         * 3) быстрое удаление из конца списка
         * remove(int index) -> O(1)
         */

        /**
         * минусы:
         * 1) вставка в начало, середину списка линейно зависит от количества элементов после позиции вставки
         * add(int index, E element) -> O(n)
         *
         * 2) удаление из начала, середины списка линейно зависит от количества элементов после позиции вставки
         * remove(int index) -> O(n)
         */

        /**
         * значение capacity в ArrayList по умолчанию равно 10
         * при вставке в массив, при превышении этого значения, массив увеличивается на значение, равное
         * lastSize * 3/2 + 1, т.е. 10, 16, 25, ...
         *
         * при удалении, размер массива не изменяется
         */

        /**
         * использование:
         * ArrayList намного быстрее и использует меньше ресурсов
         */

        final int n = 1 * 100 * 1000;

        // получение
        for (int i = 0; i < n; i++) // 1 ms
            collection.get(i);

        // вставка в конец
        for (int i = 0; i < n; i++) // 9 ms
            collection.add(i);

        // вставка в начало, середину
        for (int i = 0; i < n; i++) // 2 ms
            collection.add(0);

        // удаление из конца
//        for (int i = n - 1; i >= 0; i--) // 1 ms
//            collection.remove(i);

        // удаление из начала, середины
//        for (int i = 0; i < n; i++) // 1507 ms
//            collection.remove(0);

        long start = System.currentTimeMillis();


        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        System.out.println(timeConsumedMillis + " ms");
    }
}
