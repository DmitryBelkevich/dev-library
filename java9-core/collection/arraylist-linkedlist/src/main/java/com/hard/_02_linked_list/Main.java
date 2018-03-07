package com.hard._02_linked_list;

import java.util.Collection;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Collection<Integer> collection = new LinkedList<>();

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
    }
}
