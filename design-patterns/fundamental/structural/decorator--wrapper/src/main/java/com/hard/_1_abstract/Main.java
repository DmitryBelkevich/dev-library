package com.hard._1_abstract;

public class Main {
    public static void main(String[] args) {
        Component component =
                new QuotesDecorator(
                        new BracketsDecorator(
                                new Component1("Hello World")
                        )
                );

        String str = component.getStr();

        System.out.println(str);
    }
}

/**
 * Component
 */

interface Component {
    String getStr();
}

/**
 * Concrete Component
 */

class Component1 implements Component {
    private String str;

    public Component1(String str) {
        this.str = str;
    }

    @Override
    public String getStr() {
        return str;
    }
}

/**
 * Decorator
 */

abstract class Decorator implements Component {
    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    public String getStr() {
        return component.getStr();
    }
}

/**
 * Concrete Decorator
 */

class BracketsDecorator extends Decorator {
    public BracketsDecorator(Component component) {
        super(component);
    }

    @Override
    public String getStr() {
        return "[" + super.getStr() + "]";
    }
}

class QuotesDecorator extends Decorator {
    public QuotesDecorator(Component component) {
        super(component);
    }

    @Override
    public String getStr() {
        return "\"" + super.getStr() + "\"";
    }
}
