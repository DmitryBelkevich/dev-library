package com.hard;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        /**
         * Основан на хеш-таблицах / хеш-картах
         */
        Map<String, String> map = new HashMap<>();

        /**
         * Основан на красно-чёрном дереве
         */
//        Map<String, String> map = new TreeMap<>();

        map.put("b", "bbb");
        map.put("a", "aaa");
        map.put("c", "ccc");

        System.out.println(map);

        for (String key : map.keySet()) {
            String val = map.get(key);

            System.out.println(key + ": " + val);
        }
    }
}
