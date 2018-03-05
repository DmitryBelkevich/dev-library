package com.hard;

public class Main {
    public static void main(String[] args) {
        boolean result;

        Entity entity1 = new Entity("Hello World");
        Entity entity2 = new Entity("Hello World");

        result = entity1.equals(entity2);
        System.out.println(result); // true - все поля объекта равны
    }
}

class Entity {
    private String str;

    public Entity(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Entity) {
            Entity entity = (Entity) obj;

            String str2 = entity.getStr();

            boolean result = str.equals(str2);

            return result;
        }

        return false;
    }
}
