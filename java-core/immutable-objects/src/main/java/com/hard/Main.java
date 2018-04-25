package com.hard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        String str = "Hello World";
        Collection<Integer> collection = new ArrayList<>();
        Date date = new Date();

        Entity entity = new Entity(str, collection, date);
    }
}

/**
 * Immutable объект - это объект, состояние которого после создания невозможно изменить
 *
 * 1) все поля экземпляра класса отмечены как private final
 * 2) для полей не реализованы setters, только getters
 * 3) все поля экземпляра класса являются либо примитивами, либо immutable объектами
 * 4) для mutable объектов в getters делается clone, в конструкторе при инициализации делается clone
 * 5) класс должен быть final (чтобы subclasses не могли переопределить методы (getters))
 *
 * Immutable объекты не требуют синхронизации при многопоточном доступе
 */

final class Entity {
    private final String str;
    private final Collection<Integer> collection;
    private final Date date;

    public Entity(String str, Collection<Integer> collection, Date date) {
        this.str = str;
        this.collection = new ArrayList<>(collection);
        this.date = new Date(date.getTime());
    }

    public String getStr() {
        return str;
    }

    public Collection<Integer> getCollection() {
        return new ArrayList<>(collection);
    }

    public Date getDate() {
        return new Date(date.getTime());
    }
}
