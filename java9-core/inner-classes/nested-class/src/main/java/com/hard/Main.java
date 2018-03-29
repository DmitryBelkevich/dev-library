package com.hard;

public class Main {
    public static void main(String[] args) {
        C1.C2 c2 = new C1.C2();

        c2.getStr();
    }
}

/**
 * Nested class (статический внутренний класс, вложенный класс)
 * статический класс, определённый внутри другого класса
 *
 * 1) имеет доступ только к статическим полям и методам внешнего класса
 * 2) может иметь статические и нестатические поля и методы
 *
 * External class (внешний класс)
 *
 * 1) имеет доступ к внутренним классам (с любыми модификаторами) и к любым его полям и методам (static, non-static) (с любыми модификаторами)
 */

class C1 {
    private static String str = "Hello World";

    public static class C2 {
        public String getStr() {
            return str;
        }
    }
}
