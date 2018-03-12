package com.hard;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        /**
         * Без коллизий
         * Вставка, удаление, поиск - O(1)
         *
         * С коллизиями
         * Вставка, удаление, поиск - O(log(n))
         *
         * Все элементы имеют один хешкод
         * O(n)
         */
    }
}
