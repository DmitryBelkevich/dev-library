package com.hard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("c:/000.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /**
         * var 1
         * Данное решение опасно, потому что если в коде сгенерируется исключение, то bufferedReader.close() не будет вызван.
         * Произойдет утечка ресурса (не закроется соединение, не будет освобожден файловый дескриптор и т.д.)
         */

//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//        if (true)
//            throw new RuntimeException();
//
//        try {
//            System.out.println("closing");
//            bufferedReader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        /**
         * var 2
         * Метод close() может сгенерировать исключение.
         * И если при этом основной код работы с ресурсом тоже выбросит исключение, то оно перезатрется исключением из close().
         * Информация об исходной ошибке пропадёт: мы никогда не узнаем, что было причиной исходного исключения
         */

//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//        try {
//            throw new RuntimeException("1");
//        } finally {
//            System.out.println("closing");
//            try {
//                if (true)
//                    throw new RuntimeException("close()");
//
//                bufferedReader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        /**
         * var 3
         */

//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//        try {
//            throw new RuntimeException("1");
//        } finally {
//            System.out.println("closing");
//            try {
//                if (true)
//                    throw new RuntimeException("close()");
//
//                bufferedReader.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        /**
         * var 5
         */

        try (
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            if (true)
                throw new RuntimeException("1");
            System.out.println(bufferedReader);
        }

        /**
         * after
         */
//        try (
//                BufferedReader bufferedReader = new BufferedReader(new FileReader(""))
//        ) {
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
