package com.hard._07_volatile;

public class Main {
    private static volatile int VALUE = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            int local_value = VALUE;

            while (local_value < 5) {
                if (local_value != VALUE) {
                    System.out.println("Got Change for VALUE: " + VALUE);
                    local_value = VALUE;
                }
            }
        }).start();

        new Thread(() -> {
            int local_value = VALUE;

            while (VALUE < 5) {
                System.out.println("Incrementing VALUE to " + (local_value + 1));
                VALUE = ++local_value;

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

/**
 * Каждый поток имеет собственный стек, и собственные копии переменных.
 * Когда поток создается, все доступные переменные копируются с собственную память.
 * Volatile позволяет сказать jvm “Осторожно, эти переменные могут изменяться в других потоках”.
 * Без этого слова JVM может сделать оптимизацию, такую например, как не обновлять значение локальных копий переменных в других потоках.
 * Volatile заставляет поток обновлять исходные переменные для каждой копии.
 * Volatile может быть использована для любых типов переменных, примитивных или объектных!
 */
