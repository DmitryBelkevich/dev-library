package com.hard;

public class Main {
    public static void main(String[] args) {
        C1 c1 = new C1();

        C1.C2 c2 = c1.new C2();

        c2.getStr();
    }
}

/**
 * Inner class (внутренний класс)
 * нестатический класс, определённый внутри другого класса
 *
 * 1) имеет доступ ко всем (static, non-static) полям и методам внешнего класса (с любыми модификаторами)
 * 2) может иметь только нестатические поля и методы
 *
 * External class (внешний класс)
 *
 * 1) имеет доступ к внутренним классам (с любыми модификаторами) и к его полям и методам (static) (с любыми модификаторами)
 */

class C1 {
    private String str = "Hello World";

    public class C2 {
        public String getStr() {
            return str;
        }
    }
}
