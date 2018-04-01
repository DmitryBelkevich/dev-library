package com.hard._2_interface;

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

interface Decorator extends Component {

}

/**
 * Concrete Decorator
 */

class BracketsDecorator implements Decorator {
    private Component component;

    public BracketsDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String getStr() {
        return "[" + component.getStr() + "]";
    }
}

class QuotesDecorator implements Decorator {
    private Component component;

    public QuotesDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String getStr() {
        return "\"" + component.getStr() + "\"";
    }
}
