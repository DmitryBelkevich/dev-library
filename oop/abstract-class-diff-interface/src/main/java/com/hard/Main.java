package com.hard;

public class Main {
    public static void main(String[] args) {

    }
}

/**
 * экземпляр интерфейса и абстрактного класса создать нельзя
 * интерфейс и бстрактный класс могут содержать внутренние классы
 */

/**
 * interface:
 *
 * 1) множественная реализация (C implements I1, I2, ...)
 * 2) может содержать только public static final поля: int a = 1;
 * 3) может описывать методы: void f();
 * 4) может реализовывать только static и default методы: static void f() {}; default void f() {}
 * 5) может иметь модификаторы доступа только public
 *
 * 1) Может реализовывать интерфейсы
 *
 * Использование:
 * 1) могут быть реализованы классами которые не связаны друг с другом (class AndroidDevice implements Device; class IosDevice implements Device)
 * 2) используется, если нужна какая-то реализация по умолчанию
 * 3) расширение функциональности каждого класса-наследника ()
 *
 * default modifiers:
 * 1) все поля по-умолчанию public static final
 * 2) все методы по-умолчанию public abstract
 */
interface IEntity {

}

/**
 * abstract class
 *
 * 1) отсутствие множественного наследования (C extends A)
 * 2) может содержать поля
 * 3) может описывать методы: public abstract void f();
 * 4) может реализовывать методы: public void f() {}
 * 5) может иметь любые модификаторы доступа
 *
 * 1) Может наследоваться от классов
 * 2) Может наследоваться от абстрактных классов
 * 3) Может реализовывать интерфейсы
 *
 * в абстрактном классе можно переопределить метод с модификатором abstract
 *
 * Использование:
 * 1) используются только тогда, когда есть тип отношений "is a" (class AndroidMobile extends AndroidDevice; class AndroidTablet extends AndroidDevice)
 * 2) используется, когда классу нао задать конкретное поведение
 * 3) теряется индивидуальность класса-наследника ()
 *
 * default modifiers:
 * 1) все поля по-умолчанию default-package
 * 2) все методы по-умолчанию default-package
 */

abstract class AbstractEntity {

}

/**
 * example class
 *
 * абстрактный класс насследник не обязан реализовать абстрактные методы
 * неабстрактный класс насследник обязан реализовать абстрактные методы
 *
 *
 */

class Entity extends AbstractEntity implements IEntity {

}
