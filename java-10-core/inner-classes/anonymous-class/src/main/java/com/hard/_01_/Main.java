package com.hard._01_;

public class Main {
    public static void main(String[] args) {
        I i = new I() {
            @Override
            public String getStr() {
                return "Hello World";
            }
        };

        i.getStr();
    }
}

/**
 * Anonymous class (анонимный внутренний класс, безымянный локальный внутренний класс)
 * классы, определение которых совпадает с определением экземпляра этого класса.
 * Осюда следует, что экземпляр анонимного класса существует только один.
 */

interface I {
    String getStr();
}
