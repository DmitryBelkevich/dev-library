package com.hard;

public class Main {
    public static void main(String[] args) {
        C1 c1 = new C1();

        c1.getStr();
    }
}

/**
 * Local class (локальный внутренний класс)
 * класс, определённый внутри метода
 *
 * 1) имеет доступ ко всем (static, non-static) полям и методам внешнего класса (с любыми модификаторами)
 * 2) может иметь только нестатические поля и методы
 * 3) не имеет модификаторов доступа при объявлении
 */

class C1 {
    private String str = "Hello World";

    public String getStr() {
        class C2 {
            public String getStr() {
                return str;
            }
        }

        C2 c2 = new C2();

        return c2.getStr();
    }
}
