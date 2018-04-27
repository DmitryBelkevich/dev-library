package com.hard._03_reference_to_an_instance_method_of_an_arbitrary_object_of_a_particular_type;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] stringArray = {
                "c", "f", "b", "e", "a", "d"
        };

        Arrays.sort(stringArray, String::compareToIgnoreCase);

        for (String str : stringArray)
            System.out.println(str);
    }
}
