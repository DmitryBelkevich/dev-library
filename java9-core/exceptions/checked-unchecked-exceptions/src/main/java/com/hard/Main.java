package com.hard;

public class Main {
    public static void main(String[] args) {
        try {
            // код, который потенциально может привести к ошибке
            System.out.println("try");

            throw new RuntimeException();
        } catch (Throwable e) { // класс конкретной ожидаемой ошибки
            // код обработки исключений
            System.out.println("catch");
        } finally {
            // выполняется в любом случае, даже если в блоке catch произошло исключение
            // блок finally не обязателен
            System.out.println("finally");
        }
    }
}

/**
 * checked exceptions
 *
 * 1) проверенные (проверяются до компиляциии программы)
 * 2) контролируемые (проверить до компиляциии программы можно)
 * 3) должны обрабатываться блоком catch или описываться в сигнатуре метода
 * 4) классы-исключения, которые наследованы от Throwable или Exception (не включая unchecked)
 */

/**
 * unchecked exceptions
 *
 * 1) не проверенные (не проверяются до компиляциии программы)
 * 2) неконтролируемые (проверить до компиляциии программы нельзя)
 * 3) не должны обрабатываться блоком catch или описываться в сигнатуре метода
 * 4) классы-исключения, которые наследованы от RuntimeException или Error
 *
 * ошибки jvm обрабатывать можно, но делать этого не стоит. Разработчику не предоставлены инструменты для обработки ошибок системы и виртуальной машины.
 */
