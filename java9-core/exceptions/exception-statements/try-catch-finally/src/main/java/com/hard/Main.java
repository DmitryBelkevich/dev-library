package com.hard;

public class Main {
    public static void main(String[] args) {
        try {
            // код, который потенциально может привести к ошибке
            System.out.println("try");
        } catch (Exception e) { // класс конкретной ожидаемой ошибки
            // код обработки исключений
            System.out.println("catch");
        } finally {
            // выполняется в любом случае, даже если в блоке catch произошло исключение
            // блок finally не обязателен
            System.out.println("finally");
        }
    }
}
