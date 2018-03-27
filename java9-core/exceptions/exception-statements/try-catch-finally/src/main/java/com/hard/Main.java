package com.hard;

public class Main {
    public static void main(String[] args) {
        try {
            // код, который потенциально может привести к ошибке
        } catch (Exception e) { // класс конкретной ожидаемой ошибки
            // код обработки исключений
        } finally {
            // выполняется в любом случае
            // блок finally не обязателен
        }
    }
}
